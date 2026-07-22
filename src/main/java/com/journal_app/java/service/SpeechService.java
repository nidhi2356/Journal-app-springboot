package com.journal_app.java.service;

import com.journal_app.java.entity.JournalEntry;
import com.journal_app.java.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpeechService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Value("${elevenlabs.api.key}")
    private String apiKey;

    @Value("${elevenlabs.voice-id}")
    private String voiceId;

    public byte[] generateSpeech(ObjectId journalId) {

        // Logged-in user
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Find journal belonging to logged-in user
        JournalEntry journalEntry = user.getJournalEntries()
                .stream()
                .filter(journal -> journal.getId().equals(journalId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Journal not found or access denied"));

        String text = journalEntry.getContent();

        String url = "https://api.elevenlabs.io/v1/text-to-speech/"
                + voiceId
                + "?output_format=mp3_44100_128";

        HttpHeaders headers = new HttpHeaders();
        headers.set("xi-api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("text", text);
        requestBody.put("model_id", "eleven_multilingual_v2");

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<byte[]> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        byte[].class
                );

        return response.getBody();
    }
}
