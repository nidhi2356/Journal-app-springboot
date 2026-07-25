package com.journal_app.java.scheduler;


import com.journal_app.java.cache.AppCache;
import com.journal_app.java.entity.JournalEntry;
import com.journal_app.java.entity.User;
import com.journal_app.java.enums.Sentiment;
import com.journal_app.java.model.SentimentData;
import com.journal_app.java.repository.UserRepositoryImpl;
import com.journal_app.java.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;


    @Autowired
    private AppCache appCache;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUsersAndSendSaMail(){
        log.info("Scheduler started");
        List<User> users = userRepository.getUserForSA();
        log.info("Users found: {}", users.size());
        for(User user: users) {
            log.info("Processing user: {}", user.getEmail());
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment: sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

                Sentiment mostFrequentSentiment = null;
                int maxCount =0;
                for(Map.Entry<Sentiment,Integer> entry: sentimentCounts.entrySet()){
                    if(entry.getValue()>maxCount){
                        maxCount = entry.getValue();
                        mostFrequentSentiment = entry.getKey();
                    }
                }
                if (mostFrequentSentiment != null) {
                    SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days " + mostFrequentSentiment).build();
                    try {
                        rabbitTemplate.convertAndSend("weekly-sentiments", sentimentData);
                    } catch (Exception e) {
                        emailService.sendEmail(
                                sentimentData.getEmail(),
                                "Sentiment for previous week",
                                sentimentData.getSentiment()
                        );
                    }
                }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}
