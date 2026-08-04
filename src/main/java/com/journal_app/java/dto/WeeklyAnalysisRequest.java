package com.journal_app.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyAnalysisRequest {

    @JsonProperty("journal_entries")
    private List<String> journalEntries;

}