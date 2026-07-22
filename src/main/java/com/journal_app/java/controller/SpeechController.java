package com.journal_app.java.controller;

import com.journal_app.java.service.SpeechService;
import org.bson.types.ObjectId;
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
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    @GetMapping("/{journalId}")
    public ResponseEntity<byte[]> generateSpeech(
            @PathVariable ObjectId journalId) {

        byte[] audio = speechService.generateSpeech(journalId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=speech.mp3")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audio);
    }
}
