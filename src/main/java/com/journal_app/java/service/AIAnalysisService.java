package com.journal_app.java.service;

import com.journal_app.java.dto.WeeklyAnalysisRequest;
import com.journal_app.java.dto.WeeklyAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AIAnalysisService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ai.service.base-url}")
    private String aiServiceUrl;

    public WeeklyAnalysisResponse analyzeWeek(List<String> journalEntries) {

        WeeklyAnalysisRequest request =
                new WeeklyAnalysisRequest(journalEntries);

        return restTemplate.postForObject(
                aiServiceUrl + "/analyze-week",
                request,
                WeeklyAnalysisResponse.class
        );
    }
}
