package com.journal_app.java.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Schema(
            description = "Unique identifier of the user.",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    private ObjectId id;

    @Schema(
            description = "Unique username used for authentication.",
            example = "nidhi",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Indexed(unique = true)
    @NonNull
    private String userName;

    @Schema(
            description = "Email address of the user.",
            example = "nidhisharma00200@gmail.com"
    )
    private String email;

    @Schema(
            description = "Indicates whether sentiment analysis is enabled for the user's journal entries.",
            example = "true"
    )
    private boolean sentimentAnalysis;

    @Schema(
            description = "Password used to authenticate the user.",
            example = "nidhi",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NonNull
    private String password;

    @Schema(
            description = "List of journal entries created by the user.",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    @Schema(
            description = "Roles assigned to the user.",
            example = "[\"USER\"]"
    )
    private List<String> roles;
}
