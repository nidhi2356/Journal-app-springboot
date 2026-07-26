package com.journal_app.java.controller;


import com.journal_app.java.cache.AppCache;
import com.journal_app.java.entity.User;
import com.journal_app.java.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(
        name = "5. Admin APIs",
        description = "APIs for administrative operations such as managing users and application cache."

)
@SecurityRequirement(name = "Bearer Authentication")
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @Operation(
            summary = "Get All Users",
            description = "Retrieves a list of all registered users. This endpoint is accessible only to administrators."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "403", description = "Access denied. Administrator privileges are required."),
            @ApiResponse(responseCode = "404", description = "No users found.")
    })
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return  ResponseEntity.ok(all);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Create Admin User",
            description = "Creates a new administrator account with administrative privileges."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrator account created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid user details provided."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "403", description = "Access denied. Administrator privileges are required.")
    })
    @PostMapping("/create-admin-user")
    public ResponseEntity<String> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Administrator account created successfully.");
    }


    @Operation(
            summary = "Clear Application Cache",
            description = "Refreshes and reloads the application's cache data."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Application cache cleared successfully."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "403", description = "Access denied. Administrator privileges are required."),
            @ApiResponse(responseCode = "500", description = "An error occurred while clearing the application cache.")
    })
    @GetMapping("/clear-app-cache")
    public ResponseEntity<String> clearAppCache() {
        appCache.init();
        return ResponseEntity.ok("Application cache refreshed successfully.");
    }

}
