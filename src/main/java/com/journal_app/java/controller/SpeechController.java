package com.journal_app.java.controller;

import com.journal_app.java.service.SpeechService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speech")
@Tag(
        name = "3. Speech APIs",
        description = "APIs for converting journal entries into speech using an external Text-to-Speech (TTS) service."
)
@SecurityRequirement(name = "Bearer Authentication")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    @Operation(
            summary = "Generate Speech from Journal Entry",
            description = "Retrieves the specified journal entry, converts its textual content into speech using an external Text-to-Speech (TTS) service, and returns the generated audio in MP3 format."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Speech generated successfully."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "404", description = "Journal entry not found."),
            @ApiResponse(responseCode = "500", description = "Failed to generate speech using the external Text-to-Speech service.")
    })
    @GetMapping("/{journalId}")
    public ResponseEntity<byte[]> generateSpeech(
            @Parameter(
                    description = "Journal Entry ID",
                    example = "6a664300f765fb9f8192b23b"
            )
            @PathVariable String journalId) {

        byte[] audio = speechService.generateSpeech(journalId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=speech.mp3")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audio);
    }
}