package com.journal_app.java.controller;


import com.journal_app.java.config.RabbitMQConfig;
import com.journal_app.java.dto.LoginRequest;
import com.journal_app.java.entity.User;
import com.journal_app.java.model.SentimentData;
import com.journal_app.java.service.UserDetailsServiceImpl;
import com.journal_app.java.service.UserService;
import com.journal_app.java.utilis.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/public")
@RestController
@Slf4j
@Tag(
        name =  "1. Authentication APIs",
        description = "User Registration and Login APIs"
)
public class PublicContoller {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {

        boolean isSaved = userService.saveNewUser(user);

        if (isSaved) {
            return ResponseEntity.ok("User created successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create user");
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login User",
            description = "Authenticates the user and returns a JWT token."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful. JWT token generated."),
            @ApiResponse(responseCode = "400", description = "Invalid request body."),
            @ApiResponse(responseCode = "401", description = "Invalid username or password.")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()));

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(request.getUserName());

            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(jwt);

        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Incorrect username or password");
        }
    }

}
