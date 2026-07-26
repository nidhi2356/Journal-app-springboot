package com.journal_app.java.config;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI journalOpenAPI() {

        return new OpenAPI()

                .info(new Info()
                        .title("Journal App REST API")
                        .version("v1.0.0")
                        .description("""
                                A secure REST API for Journal App.
                                
                                Test Credentials
                                
                                Admin:
                                Username: ram
                                Password: ram
                                
                                Steps to Access Protected APIs:
                                1. Login using POST /public/login.
                                2. Copy the returned JWT token.
                                3. Click the Authorize button.
                                4. Enter:
                                   Bearer <your_jwt_token>
                                5. Click Authorize.
                                
                                Features:
                                • JWT Authentication
                                • Google OAuth2 Login
                                • Journal CRUD Operations
                                • Sentiment Analysis
                                • Weather Integration
                                • Email Notifications
                                • RabbitMQ Messaging
                                • Redis Caching
                                """)
                        .contact(new Contact()
                                .name("Nidhi Sharma")
                                .email("nidhisharma00200@gmail.com")
                        ))

                .tags(List.of(
                        new Tag()
                                .name("1. Authentication APIs")
                                .description("User Registration and Login APIs"),

                        new Tag()
                                .name("2. Journal APIs")
                                .description("CRUD operations for Journal Entries"),

                        new Tag()
                                .name("3. Speech APIs")
                                .description("APIs for converting journal entries into speech."),

                        new Tag()
                                .name("4. User APIs")
                                .description("APIs for managing the authenticated user's profile, account, and personalized greeting."),

                        new Tag()
                                .name("5. Admin APIs")
                                .description("Administrative operations such as managing users and application cache.")
                ))

                .servers(List.of(
                        new Server()
                                .url("/")
                                .description("Current Server")
                ));
    }
}