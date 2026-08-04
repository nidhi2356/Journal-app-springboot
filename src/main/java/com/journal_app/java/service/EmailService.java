package com.journal_app.java.service;

import com.journal_app.java.dto.WeeklyAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;



    public void sendEmail(String to,String subject,String body){
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            try {
                javaMailSender.send(mail);
                System.out.println("Email sent successfully");
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
    }
    
    public String buildWeeklyAnalysisEmail(WeeklyAnalysisResponse response) {

        StringBuilder email = new StringBuilder();

        email.append("🌿 WEEKLY JOURNAL REFLECTION\n\n");

        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        email.append("📖 WEEKLY SUMMARY\n");
        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        email.append(response.getWeeklySummary()).append("\n\n");

        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        email.append("😊 POSITIVE MOMENTS\n");
        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        for (String moment : response.getPositiveMoments()) {
            email.append("• ").append(moment).append("\n");
        }

        email.append("\n");

        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        email.append("⚠ CHALLENGES\n");
        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        for (String challenge : response.getChallenges()) {
            email.append("• ").append(challenge).append("\n");
        }

        email.append("\n");

        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        email.append("💡 RECOMMENDATIONS\n");
        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        for (String recommendation : response.getRecommendations()) {
            email.append("• ").append(recommendation).append("\n");
        }

        email.append("\n");

        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        email.append("🌟 MOTIVATIONAL QUOTE\n");
        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        email.append("\"")
            .append(response.getMotivationalQuote())
            .append("\"")
            .append("\n\n");

        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        email.append("🎯 NEXT WEEK FOCUS\n");
        email.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        email.append(response.getNextWeekFocus());

        return email.toString();
        }
}


