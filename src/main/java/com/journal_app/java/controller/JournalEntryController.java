package com.journal_app.java.controller;


import com.journal_app.java.entity.JournalEntry;
import com.journal_app.java.entity.User;
import com.journal_app.java.service.JournalEntryService;
import com.journal_app.java.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(
        name = "2. Journal APIs",
        description = "CRUD operations for Journal Entries"
)
@SecurityRequirement(name = "Bearer Authentication")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Get All Journal Entries",
            description = "Returns all journal entries of the logged-in user."
    )
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Journal entry created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid journal data."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated.")
    })
    @Operation(
            summary = "Create Journal Entry",
            description = "Creates a new journal entry for the authenticated user."
    )
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,
                                                    Authentication authentication) {
        try {
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Journal entry retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "404", description = "Journal entry not found.")
    })
    @Operation(
            summary = "Get Journal Entry by ID",
            description = "Retrieves a journal entry by its ID."
    )
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String myId,
                                                            Authentication authentication) {

        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Delete Journal Entry",
            description = "Deletes a journal entry."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Journal entry deleted successfully."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "404", description = "Journal entry not found.")
    })
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String myId,
                                                    Authentication authentication) {

        String userName = authentication.getName();

        boolean removed = journalEntryService.deleteById(myId, userName);

        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Update Journal Entry",
            description = "Updates an existing journal entry."
    )
    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable String id,
                                               @RequestBody JournalEntry newEntry,
                                               Authentication authentication) {

        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);

            if (journalEntry.isPresent()) {
                JournalEntry old = journalEntry.get();

                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                        ? newEntry.getTitle()
                        : old.getTitle());

                old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                        ? newEntry.getContent()
                        : old.getContent());

                journalEntryService.saveEntry(old);

                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}