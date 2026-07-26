package com.journal_app.java.entity;

import com.journal_app.java.enums.Sentiment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Document(collection = "journal_entries")
@Schema(description = "Represents a journal entry created by a user.")
public class JournalEntry {

    @Id
    @Schema(
            description = "Unique identifier of the journal entry.",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String id;

    @Schema(
            description = "Title of the journal entry.",
            example = "My First Journal",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NonNull
    private String title;

    @Schema(
            description = "Content of the journal entry.",
            example = "Today I learned how to integrate Swagger with a Spring Boot application."
    )
    private String content;

    @Schema(
            description = "Date and time when the journal entry was created.",
            example = "2026-07-26T10:30:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime date;

    @Schema(
            description = "Sentiment detected for the journal entry.",
            example = "HAPPY"
    )
    private Sentiment sentiment;

}
