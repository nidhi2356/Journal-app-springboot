<div align="center">

# 📔 Journal App

### 🚀 Production-Ready Spring Boot REST API

<p>
A secure and scalable backend application for managing personal journals, featuring JWT Authentication, Google OAuth2, Redis Caching, RabbitMQ Messaging, WeatherStack Integration, ElevenLabs Text-to-Speech, Weekly Sentiment Reports, and Swagger API Documentation.
</p>

<p>

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-47A248?style=for-the-badge&logo=mongodb)
![JWT](https://img.shields.io/badge/JWT-Authentication-blue?style=for-the-badge)
![Google OAuth2](https://img.shields.io/badge/Google-OAuth2-4285F4?style=for-the-badge&logo=google)
![Redis](https://img.shields.io/badge/Redis-Cache-DC382D?style=for-the-badge&logo=redis)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Message%20Broker-FF6600?style=for-the-badge&logo=rabbitmq)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger)
![SonarQube](https://img.shields.io/badge/SonarQube-Code%20Quality-4E9BCD?style=for-the-badge&logo=sonarqube)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)

</p>

</div>

---

# 📖 About The Project

Journal App is a **production-ready backend REST API** developed using **Spring Boot 4.1.0** and **Java 21**. It is designed to demonstrate modern backend engineering practices including secure authentication, asynchronous messaging, caching, scheduled background jobs, third-party API integrations, and clean layered architecture.

Unlike a traditional CRUD application, this project focuses on solving real-world backend problems such as secure authentication, performance optimization, asynchronous processing, scheduled automation, and maintainable system design.

The application enables users to securely maintain journal entries, authenticate using either **JWT** or **Google OAuth2**, generate audio versions of journals using the **ElevenLabs API**, retrieve live weather information through **WeatherStack**, and receive an automated **weekly sentiment summary email** based on their journal activity.

---

# 🌟 Key Highlights

- 🔐 Dual Authentication System (JWT + Google OAuth2)
- 📔 Secure Journal Management
- 📧 Automated Weekly Sentiment Email Reports
- ⚡ Redis Configuration Caching
- 📨 RabbitMQ Asynchronous Email Processing
- 🌦 WeatherStack API Integration
- 🎤 ElevenLabs Text-to-Speech Integration
- 📑 Interactive Swagger Documentation
- 🧪 JUnit Testing
- 📝 Professional Logging using SLF4J & Logback
- 🔍 Code Quality Analysis with SonarQube
- 🚀 Deployment Ready (Render)

---

# 🎯 Project Objectives

This project was developed to demonstrate practical backend development concepts commonly used in production-grade applications.

Major objectives include:

- Designing secure REST APIs using Spring Security
- Implementing stateless authentication with JWT
- Supporting social login using Google OAuth2
- Improving application performance using Redis
- Implementing asynchronous processing with RabbitMQ
- Automating scheduled tasks using Spring Scheduler
- Integrating multiple third-party REST APIs
- Maintaining clean architecture and code quality
- Building a scalable and maintainable backend system

---

# 📊 Project Overview

| Property | Details |
|-----------|----------|
| Project Type | Backend REST API |
| Language | Java 21 |
| Framework | Spring Boot 4.1.0 |
| Architecture | Layered Architecture |
| Database | MongoDB Atlas |
| Authentication | JWT + Google OAuth2 |
| Security | Spring Security |
| Cache | Redis |
| Messaging | RabbitMQ |
| External APIs | WeatherStack, ElevenLabs |
| Documentation | Swagger / OpenAPI |
| Logging | SLF4J + Logback |
| Testing | JUnit |
| Code Quality | SonarQube & SonarLint |
| Deployment | Render |
| REST APIs | ~15 |

---

# 📑 Table of Contents

- 📖 About The Project
- ✨ Features
- 🏗 System Architecture
- 🔐 Authentication Flow
- 📔 Journal Workflow
- 📧 Weekly Sentiment Workflow
- 📨 RabbitMQ Workflow
- ⚡ Redis Cache Workflow
- 🎤 ElevenLabs Integration
- 🌦 WeatherStack Integration
- 📂 Project Structure
- 🛠 Technology Stack
- 📡 API Endpoints
- 🚀 Getting Started
- ⚙ Configuration
- 📑 API Documentation
- 📸 Screenshots
- 🧪 Testing
- 🔍 Code Quality
- 💡 Engineering Decisions
- 🚀 Future Enhancements
- 👩‍💻 Author
# ✨ Features

The Journal App is a production-ready backend REST API that combines secure authentication, asynchronous messaging, scheduled automation, caching, third-party integrations, and clean software architecture. It demonstrates backend development concepts commonly used in real-world applications.

---

# 🔐 Authentication & Security

The application provides a secure authentication system using Spring Security with support for both traditional login and Google OAuth2.

### Key Features

- User Registration
- Username & Password Login
- JWT Authentication
- Google OAuth2 Login
- Spring Security Integration
- BCrypt Password Encryption
- Role-Based Authorization
- Stateless Authentication
- Protected REST Endpoints

### Highlights

✔ Secure user registration

✔ JWT generation after successful authentication

✔ Login using Google account

✔ Automatic user creation on first Google login

✔ Passwords encrypted using BCrypt

✔ Separate access control for User and Admin APIs

---

# 📔 Journal Management

The application allows authenticated users to manage their personal journal entries securely. Every journal is associated with its owner and includes a manually selected sentiment.

### Features

- Create Journal Entry
- Update Journal Entry
- Delete Journal Entry
- View Individual Journal
- View All Personal Journals
- Ownership Validation
- Sentiment Tracking

### Supported Sentiments

Each journal entry contains one of the following predefined sentiment values:

- 😊 Happy
- 😔 Sad
- 😡 Angry
- 😟 Anxious

These sentiments are stored with every journal entry and are later analyzed by the weekly scheduler to identify the user's dominant emotion.

---

# 📧 Weekly Sentiment Summary

The application automatically generates a weekly sentiment report using Spring Scheduler.

Every **Sunday at 9:00 AM**, the scheduler:

- Retrieves journal entries from the previous week
- Counts the occurrence of each sentiment
- Determines the dominant sentiment
- Publishes an email request to RabbitMQ
- Sends a personalized summary email to eligible users

Only users who have:

- Enabled sentiment analysis
- Added a valid email address

receive the weekly report.

---

# 📨 RabbitMQ Integration

RabbitMQ is used for asynchronous email processing.

Instead of sending emails directly from the scheduler, email requests are published to a RabbitMQ queue where they are processed independently by a dedicated consumer.

### Benefits

- Asynchronous Processing
- Loose Coupling
- Improved Scalability
- Faster Scheduler Execution
- Reliable Message Delivery

### Components Used

- Exchange
- Queue
- Producer
- Consumer
- Routing Key

---

# ⚡ Redis Integration

Redis is used as a configuration cache to improve application performance.

The WeatherStack API configuration is cached in Redis, reducing repeated database queries.

If the configuration is unavailable in Redis, it is automatically retrieved from MongoDB and stored in the cache.

### Features

- Configuration Caching
- Automatic Cache Refresh
- Manual Cache Invalidation
- Reduced MongoDB Queries
- Faster Response Time

---

# 🌦 WeatherStack Integration

The application integrates with the WeatherStack REST API to retrieve real-time weather information.

The WeatherStack API key is securely stored in MongoDB and cached using Redis for improved performance.

### Features

- Live Weather Information
- REST API Integration
- JSON Response Processing
- Redis-backed Configuration Cache

---

# 🎤 ElevenLabs Text-to-Speech

Users can convert journal entries into realistic speech using the ElevenLabs API.

The application retrieves the journal content, sends it to the ElevenLabs service, and returns the generated audio.

### Features

- Text-to-Speech Conversion
- External REST API Integration
- Audio Generation
- HTTP POST Communication

---

# 📑 Swagger / OpenAPI Documentation

Interactive API documentation is available through Swagger UI.

Developers can explore and test every endpoint directly from the browser.

### Features

- Interactive Documentation
- JWT Authorization Support
- Try-It-Out Functionality
- Request & Response Models
- Organized API Groups

---

# 📝 Logging

The application uses **SLF4J** with **Logback** for structured application logging.

Logging helps monitor application behavior, simplify debugging, and track runtime events.

### Logging Includes

- Application Startup Logs
- Error Logging
- Exception Tracking
- Service-Level Logs
- Request Processing Logs

---

# 🧪 Testing

JUnit is used to validate business logic and improve application reliability.

### Test Coverage

- Service Layer Testing
- Repository Testing
- Authentication Logic
- Business Rule Validation

---

# 🔍 Code Quality

Code quality is maintained using **SonarQube** and **SonarLint**.

Static code analysis helps identify issues early and ensures the project remains maintainable and secure.

### Analysis Includes

- Bugs
- Vulnerabilities
- Security Hotspots
- Code Smells
- Maintainability Rating
- Reliability Rating
- Security Rating
- Technical Debt
# 🏗 System Architecture

The Journal App follows a layered architecture that separates responsibilities into Controllers, Services, Repositories, and the Database. Additional components such as Redis, RabbitMQ, Spring Scheduler, and external APIs are integrated to improve scalability, maintainability, and performance.

```text
                                    Client
                                       │
                                       ▼
                          HTTP / HTTPS Requests
                                       │
                                       ▼
                              Spring Security
                         JWT Filter / OAuth2 Login
                                       │
                                       ▼
                                 REST Controllers
                                       │
             ┌───────────────┬─────────┴─────────┬───────────────┐
             ▼               ▼                   ▼               ▼
      User Service     Journal Service    Weather Service   Speech Service
             │               │                   │               │
             │               │                   │               │
             ▼               ▼                   ▼               ▼
      User Repository  Journal Repository  Redis Cache     ElevenLabs API
             │               │                   │
             └───────────────┴──────────┬────────┘
                                        ▼
                                  MongoDB Atlas
                                        ▲
                                        │
                              Configuration Data
                                        │
                                        ▼
                                   WeatherStack API

                Spring Scheduler
                       │
                       ▼
          Weekly Sentiment Calculation
                       │
                       ▼
               RabbitMQ Producer
                       │
                       ▼
                 RabbitMQ Queue
                       │
                       ▼
              RabbitMQ Consumer
                       │
                       ▼
                  Email Service
                       │
                       ▼
                 User Email Inbox
```

---

# 📐 Application Layers

## 1️⃣ Controller Layer

The Controller layer exposes REST endpoints and handles incoming HTTP requests.

### Responsibilities

- Receive client requests
- Validate request parameters
- Delegate business logic to services
- Return HTTP responses

Current Controllers

- PublicController
- UserController
- JournalEntryController
- AdminController
- SpeechController

---

## 2️⃣ Service Layer

The Service layer contains the application's business logic.

Responsibilities include:

- User Authentication
- Journal Management
- Weather Integration
- Redis Cache Handling
- RabbitMQ Messaging
- Email Processing
- Speech Generation
- Weekly Sentiment Analysis

---

## 3️⃣ Repository Layer

Repositories provide communication between the application and MongoDB.

Repositories used:

- UserRepository
- JournalEntryRepository
- ConfigJournalAppRepository

---

## 4️⃣ Database Layer

MongoDB Atlas stores all persistent application data.

Collections include:

- users
- journal_entries
- config_journal_app

---

# 🔐 Authentication Flow

```text
                User
                  │
                  ▼
      Username & Password Login
                  │
                  ▼
         Spring Security
                  │
                  ▼
      UserDetailsService
                  │
                  ▼
       Validate Credentials
                  │
          Success / Failure
                  │
                  ▼
          Generate JWT Token
                  │
                  ▼
        Return JWT to Client
                  │
                  ▼
 Client sends JWT in Authorization Header
                  │
                  ▼
             JWT Filter
                  │
                  ▼
        Protected REST Endpoints
```

---

# 🌐 Google OAuth2 Flow

```text
                User
                  │
                  ▼
       Login with Google
                  │
                  ▼
       Google Authentication
                  │
                  ▼
   OAuth2LoginSuccessHandler
                  │
                  ▼
      User Exists in MongoDB?
            │             │
          Yes             No
           │              │
           │        Create New User
           └──────────────┘
                  │
                  ▼
         Generate JWT Token
                  │
                  ▼
        Return Token to Client
                  │
                  ▼
        Access Protected APIs
```

---

# 📔 Journal Management Workflow

```text
          Authenticated User
                  │
                  ▼
          Create Journal Entry
                  │
                  ▼
        Select Sentiment
(Happy / Sad / Angry / Anxious)
                  │
                  ▼
       Save Journal in MongoDB
                  │
                  ▼
    Journal Associated with User
```

---

# 📧 Weekly Sentiment Workflow

```text
          Spring Scheduler
      (Every Sunday - 9:00 AM)
                  │
                  ▼
      Retrieve Weekly Journals
                  │
                  ▼
      Count Sentiment Frequency
                  │
                  ▼
    Determine Dominant Sentiment
                  │
                  ▼
     Publish Email Request
                  │
                  ▼
            RabbitMQ Queue
                  │
                  ▼
        RabbitMQ Consumer
                  │
                  ▼
          EmailService
                  │
                  ▼
      Weekly Sentiment Email
```

---

# 📨 RabbitMQ Workflow

```text
Weekly Sentiment Scheduler
             │
             ▼
     RabbitMQ Producer
             │
             ▼
         Exchange
             │
             ▼
           Queue
             │
             ▼
RabbitMQ Consumer Service
             │
             ▼
        Email Service
             │
             ▼
       User Receives Email
```

---

# ⚡ Redis Cache Workflow

```text
      Weather Request
             │
             ▼
      Check Redis Cache
             │
      ┌──────┴──────┐
      │             │
   Cache Hit    Cache Miss
      │             │
      ▼             ▼
 Return API    Read MongoDB
    Config          │
                    ▼
           Store in Redis
                    │
                    ▼
          Call WeatherStack API
```

---

# 🌦 WeatherStack Integration

```text
User Request
      │
      ▼
Weather Controller
      │
      ▼
Weather Service
      │
      ▼
Redis Cache
      │
      ▼
WeatherStack API
      │
      ▼
Weather Response
      │
      ▼
Return JSON Response
```

---

# 🎤 ElevenLabs Integration

```text
User Request
      │
      ▼
Speech Controller
      │
      ▼
Retrieve Journal Entry
      │
      ▼
Journal Service
      │
      ▼
ElevenLabs API
      │
      ▼
Generated Audio
      │
      ▼
Return Audio Response
```

---

# 💡 Why This Architecture?

This architecture was designed to keep the application modular, scalable, and easy to maintain.

### Design Principles

- Separation of Concerns
- Layered Architecture
- Stateless Authentication
- Asynchronous Processing
- High Performance through Caching
- Loose Coupling using RabbitMQ
- External API Abstraction
- Clean Service-Oriented Design
# 📂 Project Structure

The project follows a clean layered architecture to separate concerns and improve maintainability.

```text
Journal-App
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.journal_app.java
│   │   │
│   │   ├── config
│   │   │      ├── OpenApiConfig
│   │   │      ├── PasswordConfig
│   │   │      ├── RabbitMQConfig
│   │   │      ├── RedisConfig
│   │   │      └── SpringSecurity
│   │   │
│   │   ├── controller
│   │   │      ├── AdminController
│   │   │      ├── JournalEntryController
│   │   │      ├── PublicController
│   │   │      ├── SpeechController
│   │   │      └── UserController
│   │   │
│   │   ├── dto
│   │   │
│   │   ├── entity
│   │   │      ├── User
│   │   │      ├── JournalEntry
│   │   │      └── ConfigJournalAppEntity
│   │   │
│   │   ├── enums
│   │   │      └── Sentiment
│   │   │
│   │   ├── filter
│   │   │      └── JwtFilter
│   │   │
│   │   ├── handler
│   │   │      └── OAuth2LoginSuccessHandler
│   │   │
│   │   ├── repository
│   │   │      ├── UserRepository
│   │   │      ├── JournalEntryRepository
│   │   │      ├── ConfigJournalAppRepository
│   │   │      └── UserRepositoryImpl
│   │   │
│   │   ├── scheduler
│   │   │      └── UserScheduler
│   │   │
│   │   ├── service
│   │   │      ├── EmailService
│   │   │      ├── JournalEntryService
│   │   │      ├── RedisService
│   │   │      ├── SentimentConsumerService
│   │   │      ├── SpeechService
│   │   │      ├── UserDetailsServiceImpl
│   │   │      ├── UserService
│   │   │      └── WeatherService
│   │   │
│   │   └── utils
│   │
│   └── resources
│       ├── application.yml
│       ├── application.properties
│       ├── logback.xml
│       ├── static
│       └── templates
│
└── pom.xml
```

---

# 🛠 Technology Stack

| Category | Technology |
|----------|------------|
| Language | Java 21 |
| Framework | Spring Boot 4.1.0 |
| Build Tool | Maven |
| Database | MongoDB Atlas |
| Security | Spring Security |
| Authentication | JWT + Google OAuth2 |
| Cache | Redis |
| Message Broker | RabbitMQ |
| API Documentation | Swagger / OpenAPI |
| Weather API | WeatherStack |
| Text-to-Speech | ElevenLabs |
| Logging | SLF4J + Logback |
| Testing | JUnit |
| Code Quality | SonarQube & SonarLint |
| Deployment | Render |

---

# 📡 REST API Overview

The application exposes REST APIs grouped by functionality.

| Controller | Description |
|------------|-------------|
| PublicController | Public endpoints such as user registration, login, weather, and Google OAuth2 authentication |
| UserController | User profile management and user-specific operations |
| JournalEntryController | CRUD operations for journal entries |
| AdminController | Administrative operations including cache management |
| SpeechController | Convert journal entries into speech using ElevenLabs |

---

# 🔗 Major API Groups

## 👤 Authentication APIs

- User Registration
- User Login
- Google OAuth2 Login
- JWT Authentication

---

## 📔 Journal APIs

- Create Journal
- Update Journal
- Delete Journal
- Get Journal by ID
- Get All Journals

---

## 🌦 Weather APIs

- Get Current Weather

---

## 🎤 Speech APIs

- Convert Journal Entry to Speech

---

## 👨‍💼 Admin APIs

- Refresh Redis Cache
- Clear Redis Cache
- Administrative Operations

---

# 🚀 Getting Started

## Prerequisites

Before running the project, ensure you have the following installed:

- Java 21
- Maven 3.9+
- MongoDB Atlas Account
- Redis Server
- RabbitMQ Server
- Git

---

## Clone Repository

```bash
git clone https://github.com/nidhi2356/Journal-app-springboot.git
```

Move into the project directory:

```bash
cd Journal-app-springboot
```

---

## Install Dependencies

```bash
mvn clean install
```

---

## Run the Application

```bash
mvn spring-boot:run
```

The application starts at:

```
http://localhost:8080
```

---

# ⚙ Environment Variables

Create an `application.properties` or configure the following values before running the application.

| Property | Description |
|----------|-------------|
| MongoDB URI | MongoDB Atlas connection string |
| JWT Secret | Secret key used for JWT generation |
| Google Client ID | Google OAuth2 Client ID |
| Google Client Secret | Google OAuth2 Client Secret |
| Redis Host | Redis server host |
| Redis Port | Redis server port |
| RabbitMQ Host | RabbitMQ server host |
| RabbitMQ Username | RabbitMQ username |
| RabbitMQ Password | RabbitMQ password |
| WeatherStack API Key | API key for WeatherStack |
| ElevenLabs API Key | API key for ElevenLabs |
| Mail Username | Email account used for sending emails |
| Mail Password | Email application password |

---

# 📑 API Documentation

Swagger UI is available after the application starts.

```
http://localhost:8080/swagger-ui/index.html
```

Using Swagger, developers can:

- Explore available endpoints
- Authenticate using JWT
- Execute API requests
- Inspect request and response models
- Test APIs without external tools

---

# 🌍 Deployment

The application is deployed on **Render**.

Before deployment:

- Configure all environment variables in Render.
- Update the Google OAuth2 redirect URI.
- Configure MongoDB Atlas network access.
- Configure Redis and RabbitMQ instances.
- Verify WeatherStack and ElevenLabs API keys.
- Ensure Swagger is accessible in the deployed environment.
# 📸 Screenshots

The following screenshots provide a quick overview of the application's API documentation and database structure.

---

## 📑 Swagger UI

Interactive API documentation generated using OpenAPI. Developers can explore, authorize, and test REST endpoints directly from the browser.

<p align="center">
    <img src="assets/screenshots/swagger-ui.png"
         alt="Swagger UI"
         width="100%">
</p>

---

## 🍃 MongoDB Atlas

MongoDB Atlas is used as the primary NoSQL database for storing user information, journal entries, and application configuration.

<p align="center">
    <img src="assets/screenshots/mongodb-atlas.png"
         alt="MongoDB Atlas"
         width="90%">
</p>
# 💡 Key Engineering Decisions

This project was designed with maintainability, scalability, and performance in mind.

| Decision | Reason |
|----------|--------|
| Spring Boot | Rapid development of production-ready REST APIs |
| JWT Authentication | Stateless and secure authentication mechanism |
| Google OAuth2 | Simplifies login while maintaining secure authorization |
| MongoDB Atlas | Flexible NoSQL database suitable for journal data |
| Redis | Reduces repeated MongoDB lookups by caching WeatherStack configuration |
| RabbitMQ | Decouples scheduled tasks from email delivery for improved scalability |
| Spring Scheduler | Automates weekly sentiment analysis and background tasks |
| WeatherStack API | Provides real-time weather information |
| ElevenLabs API | Converts journal entries into natural-sounding speech |
| Swagger | Interactive API documentation for testing and development |
| SonarQube | Maintains code quality through static analysis |
| SLF4J + Logback | Structured logging for monitoring and debugging |

---

# 🚀 Future Enhancements

The following features can further enhance the application:

- 📱 Frontend using React or Angular
- 📊 Personal sentiment analytics dashboard
- 📈 Monthly sentiment trends
- 🤖 AI-powered sentiment detection
- 📎 Image attachments in journal entries
- 🔍 Full-text journal search
- ⭐ Favorite journal entries
- 📂 Journal categories and tags
- 📤 PDF export of journals
- ☁ Docker containerization
- ☸ Kubernetes deployment
- 🔄 CI/CD pipeline using GitHub Actions
- 📊 Monitoring with Prometheus & Grafana

---

# 🤝 Contributing

Contributions are welcome.

If you have suggestions for improvements or new features:

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push the branch
5. Open a Pull Request

---

# 👩‍💻 Author

**Nidhi Sharma**

Backend Developer | Java & Spring Boot Enthusiast

- GitHub: https://github.com/nidhi2356
- LinkedIn: _Add your LinkedIn profile link here_

---

# 🙏 Acknowledgements

Special thanks to the open-source community and the teams behind:

- Spring Boot
- MongoDB Atlas
- Redis
- RabbitMQ
- Swagger / OpenAPI
- WeatherStack
- ElevenLabs
- SonarQube

---

# ⭐ Support

If you found this project helpful:

⭐ Star this repository

🍴 Fork the project

📢 Share your feedback

Your support helps improve the project and encourages future development.

---

<div align="center">

### 🚀 Built with Java, Spring Boot, and a passion for Backend Development ❤️

</div>
