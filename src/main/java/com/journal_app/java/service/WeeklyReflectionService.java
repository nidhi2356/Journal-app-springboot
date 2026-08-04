package com.journal_app.java.service;

import java.time.LocalDateTime;
import java.util.List;

import com.journal_app.java.dto.WeeklyAnalysisResponse;
import com.journal_app.java.entity.JournalEntry;
import com.journal_app.java.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class WeeklyReflectionService {

    @Autowired
    private AIAnalysisService aiAnalysisService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    public void generateAndSendWeeklyReflection(String userName) {

        User user = userService.findByUserName(userName);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        log.info("Processing user: {}", user.getEmail());

        List<JournalEntry> journalEntries = user.getJournalEntries();

        List<String> journalContents = journalEntries.stream()
                .filter(journal -> journal.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                .map(JournalEntry::getContent)
                .filter(Objects::nonNull)
                .filter(content -> !content.isBlank())
                .toList();

        if (journalContents.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No journal entries found for the last 7 days.");
        }

        WeeklyAnalysisResponse response =
                aiAnalysisService.analyzeWeek(journalContents);

        String emailBody =
                emailService.buildWeeklyAnalysisEmail(response);

        emailService.sendEmail(
                user.getEmail(),
                "🌿 Your Weekly Journal Reflection",
                emailBody
        );

        log.info("Weekly reflection email sent successfully to {}", user.getEmail());
    }
}