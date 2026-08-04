package com.journal_app.java.scheduler;


import com.journal_app.java.dto.WeeklyAnalysisResponse;
import com.journal_app.java.service.AIAnalysisService;
import com.journal_app.java.cache.AppCache;
import com.journal_app.java.entity.JournalEntry;
import com.journal_app.java.entity.User;
import com.journal_app.java.repository.UserRepositoryImpl;
import com.journal_app.java.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private AIAnalysisService aiAnalysisService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;


    @Autowired
    private AppCache appCache;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0 0 9 ? * SUN")
    public void sendWeeklyReflection(){
        log.info("Scheduler started");
        List<User> users = userRepository.getUserForSA();
        log.info("Users found: {}", users.size());
        for(User user: users) {
            log.info("Processing user: {}", user.getEmail());
            List<JournalEntry> journalEntries = user.getJournalEntries();
            // Last 7 days journal contents for AI
            List<String> journalContents = journalEntries.stream()
                    .filter(journal -> journal.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getContent)
                    .filter(Objects::nonNull)
                    .filter(content -> !content.isBlank())
                    .toList();
            if (journalContents.isEmpty()) {
                log.info("No journal entries found for {}", user.getEmail());
                continue;
            }
            try {

                WeeklyAnalysisResponse response =
                        aiAnalysisService.analyzeWeek(journalContents);

                log.info("AI Response : {}", response);

                String emailBody = emailService.buildWeeklyAnalysisEmail(response);

                // For now just send summary.
                // Later we'll create a beautiful HTML email.
                emailService.sendEmail(
                        user.getEmail(),
                        "🌿 Your Weekly Journal Reflection",
                        emailBody
                );

            } catch (Exception e) {

                log.error("AI Analysis Failed for {}", user.getEmail(), e);

            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}
