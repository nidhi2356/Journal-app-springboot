package com.journal_app.java.config;

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

                        .description("""
                                A secure REST API for Journal App.

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

                        .version("v1.0.0")

                        .contact(new Contact()
                                .name("Nidhi Sharma")
                                .email("nidhisharma00200@gmail.com")
                        ))

                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server")
                ));
    }
}