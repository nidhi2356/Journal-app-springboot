package com.journal_app.java.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.journal_app.java.service.WeeklyReflectionService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;



@RestController
@RequestMapping("/weekly-reflection")
@Tag(
        name = "6. Weekly Reflection APIs",
        description = "Generate and send AI-powered weekly journal reflection."
)
@SecurityRequirement(name = "Bearer Authentication")
public class WeeklyReflectionController {

    @Autowired
    private WeeklyReflectionService weeklyReflectionService;

    @Operation(
            summary = "Generate Weekly Reflection",
            description = "Generates and sends the weekly AI reflection email to the authenticated user."
    )
    @PostMapping("/send")
    public ResponseEntity<String> sendWeeklyReflection(Authentication authentication) {

        weeklyReflectionService.generateAndSendWeeklyReflection(
                authentication.getName()
        );

        return ResponseEntity.ok(
                "Weekly reflection email sent successfully."
        );
    }
}