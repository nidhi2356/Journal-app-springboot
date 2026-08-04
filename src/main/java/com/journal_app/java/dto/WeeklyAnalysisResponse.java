package com.journal_app.java.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeeklyAnalysisResponse {

    private String weeklySummary;

    private List<String> positiveMoments;

    private List<String> challenges;

    private List<String> recommendations;

    private String motivationalQuote;

    private String nextWeekFocus;

}