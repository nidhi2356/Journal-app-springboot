# 📔 Journal App - Production Ready Spring Boot REST API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)
![MongoDB](https://img.shields.io/badge/Database-MongoDB-green)
![JWT](https://img.shields.io/badge/Security-JWT-blue)
![Redis](https://img.shields.io/badge/Cache-Redis-red)
![Kafka](https://img.shields.io/badge/Messaging-Apache%20Kafka-black)
![Swagger](https://img.shields.io/badge/API-Swagger-success)
![SonarQube](https://img.shields.io/badge/Code%20Quality-SonarQube-blueviolet)
![Maven](https://img.shields.io/badge/Build-Maven-red)

A production-ready **Journal Management REST API** built using **Spring Boot** and **Java 21**. The application demonstrates modern backend development practices including authentication, caching, asynchronous messaging, external API integration, logging, testing, API documentation, and clean layered architecture.

---

# 🚀 Features

## 👤 User Management

- User Registration
- Secure Login
- Update User Profile
- Delete User Account
- Password Encryption using BCrypt
- JWT Token Generation & Validation
- Role-Based Authorization (USER & ADMIN)

---

## 📖 Journal Management

- Create Journal Entry
- Update Journal Entry
- Delete Journal Entry
- View Individual Journal Entry
- View All Journal Entries
- User-specific Journal Mapping

---

## 🔐 Authentication & Security

Implemented using **Spring Security + JWT**

### Features

- JWT Authentication
- Stateless Authentication
- Spring Security
- BCrypt Password Encoding
- Role-Based Authorization
- Public & Protected APIs
- Custom Authentication Filter
- Secure REST Endpoints

---

# 🌦 External API Integrations

## Weather API Integration

Integrated a real-time Weather API using **RestTemplate**.

### Features

- Live Weather Information
- External REST API Communication
- Exception Handling
- Service Layer Abstraction

---

## 🎙 ElevenLabs Integration

Integrated ElevenLabs API to demonstrate third-party API communication.

Features include:

- External API Integration
- Secure API Key Configuration
- REST Client Communication

---

## 👋 Greeting Service

- Dynamic greeting generation
- Personalized welcome messages
- Demonstrates service abstraction

---

# 🍃 MongoDB Integration

Database:

- MongoDB Atlas

Implemented using:

- Spring Data MongoDB
- MongoRepository
- MongoTemplate
- Document Mapping
- DBRef Relationships
- Custom Queries

---

# ⚡ Redis Integration

Implemented Redis to improve application performance.

### Features

- Redis Cache
- Faster API Responses
- Reduced Database Hits
- Cached Frequently Used Data

---

# 📨 Apache Kafka

Implemented asynchronous communication using Apache Kafka.

### Features

- Kafka Producer
- Kafka Consumer
- Event Publishing
- Event Consumption
- Decoupled Architecture

---

# 🛠 MongoTemplate

Used MongoTemplate for advanced database operations.

Examples include:

- Dynamic Queries
- Complex Filtering
- Custom Mongo Queries
- Flexible Database Operations

---

# ⚙ PostConstruct

Used **@PostConstruct** for initialization logic.

Examples include:

- Startup Configuration
- Bean Initialization
- Loading Initial Data

---

# 📑 Swagger Documentation

Integrated Swagger / OpenAPI.

Features:

- Interactive API Documentation
- Test APIs from Browser
- Automatic Endpoint Documentation

---

# 📝 Logging

Implemented professional logging using **SLF4J**.

Features

- INFO Logs
- WARN Logs
- ERROR Logs
- Debug Logging
- Replaced System.out.println()

---

# 🧪 Testing

Implemented testing using:

- JUnit
- Spring Boot Test
- Service Layer Tests

---

# 🔍 Code Quality

## SonarLint

- Detect Code Smells
- Detect Bugs
- Improve Maintainability
- Follow Best Practices

## SonarQube

- Static Code Analysis
- Security Analysis
- Reliability Analysis
- Maintainability Checks
- Technical Debt Monitoring

---

# 🏗 Project Architecture

The project follows a clean layered architecture.

```
Client
   │
   ▼
JWT Authentication
   │
   ▼
Spring Security Filter
   │
   ▼
Controllers
   │
   ▼
Services
   │
   ├────────► Redis Cache
   │
   ├────────► Kafka Producer
   │
   ├────────► Weather API
   │
   ├────────► ElevenLabs API
   │
   ▼
Repositories
   │
   ▼
MongoDB Atlas
```

---

# 📂 Project Structure

```
src/main/java
│
├── api.response
├── cache
├── config
├── constants
├── controller
├── dto
├── entity
├── enums
├── filter
├── model
├── repository
├── scheduler
├── service
├── utils
└── JournalApplication
```

---

# 🛠 Tech Stack

| Category | Technology |
|-----------|------------|
| Language | Java 21 |
| Framework | Spring Boot |
| Security | Spring Security |
| Authentication | JWT |
| Database | MongoDB Atlas |
| Database Access | MongoRepository, MongoTemplate |
| Cache | Redis |
| Messaging | Apache Kafka |
| Documentation | Swagger / OpenAPI |
| Build Tool | Maven |
| Testing | JUnit |
| Logging | SLF4J |
| Code Quality | SonarLint, SonarQube |
| External API | RestTemplate |
| Version Control | Git & GitHub |

---

# ⚙ Installation

## Clone Repository

```bash
git clone https://github.com/nidhi2356/Journal-app-springboot.git
```

Navigate into the project

```bash
cd Journal-app-springboot
```

Build

```bash
mvn clean install
```

Run

```bash
mvn spring-boot:run
```

---

# 🔧 Configuration

Configure the following before running:

- MongoDB Atlas URI
- JWT Secret Key
- Redis Configuration
- Kafka Configuration
- Weather API Key
- ElevenLabs API Key

---

# 📑 API Documentation

After starting the application:

```
http://localhost:8080/swagger-ui/index.html
```

---

# 💡 Backend Concepts Demonstrated

- RESTful API Development
- Spring Boot
- Spring Security
- JWT Authentication
- Role-Based Authorization
- BCrypt Password Encoding
- MongoDB Atlas
- MongoRepository
- MongoTemplate
- Redis Caching
- Apache Kafka
- External API Integration
- RestTemplate
- Swagger
- Logging
- Exception Handling
- DTO Pattern
- Layered Architecture
- Dependency Injection
- @PostConstruct
- Scheduler
- SonarLint
- SonarQube
- JUnit Testing
- Maven
- Clean Code Practices

---

# 🚀 Future Enhancements

- Docker Support
- CI/CD Pipeline
- Email Notifications
- AI-powered Journal Summarization
- Elasticsearch
- Kubernetes Deployment
- Prometheus & Grafana Monitoring

---

# 👩‍💻 Author

**Nidhi Sharma**

Backend Developer | Java | Spring Boot | MongoDB | Redis | Kafka

GitHub: https://github.com/nidhi2356

Repository:

https://github.com/nidhi2356/Journal-app-springboot

---

⭐ If you found this project useful, consider giving it a **Star**.
