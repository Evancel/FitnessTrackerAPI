# 🏃‍♀️ Fitness Tracker API

A secure, token-regulated backend API for managing fitness activity data submitted by registered developer applications. Built with **Spring Boot**, this project follows **Clean Architecture** principles, focuses on maintainability, and includes strong validation, layered domain design, and token bucket rate limiting.

> **Note:** This was a learning project completed during our educational program. Throughout the development, we explored various backend topics such as Clean Architecture, domain-driven design (DDD), API security, and rate limiting strategies.

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot (Web, Data JPA, Security, Validation)
- H2 in-memory database
- Jakarta Validation
- JUnit 5 + Mockito
- Token bucket rate limiting (for BASIC-tier apps)
- Clean Architecture principles

---

## 🚀 Features

### 👤 Developer Management
- `POST /api/developers/signup` – Register a new developer
- `GET /api/developers/{id}` – Get profile of the authenticated developer

### 📱 Application Registration
- `POST /api/applications/register` – Register a new fitness app for a developer
- Each app is assigned a unique API key

### 🏋️ Fitness Tracking
- `POST /api/tracker` – Submit fitness activity (authenticated via API key)
- `GET /api/tracker` – View all submitted activities with pagination
- Rate limiting for BASIC-tier apps (HTTP 429 on limit breach)

---

## ✅ Sample JSON Payloads

### Developer Registration
**Endpoint:** `POST /api/developers/signup`  
**Payload:**
```json
{
  "email": "john@example.com",
  "password": "StrongPass123"
}
```

### Application Registration
**Endpoint:** `POST /api/applications/register`  
**Payload:**
```json
{
  "name": "MyWorkoutApp",
  "description": "Tracks cardio and strength exercises",
  "category": "basic"
}
```

### Submit Fitness activity
**Endpoint:** `POST /api/tracker`  
**Payload:**
Headers: X-API-Key: abc123xyz
```json
{
   "username": "user-12",
   "activity": "swimming",
   "duration": 950,
   "calories": 320
}
```

---

## 🔐 Security & Rate Limiting

- Stateless API key-based authentication
- Custom global exception handling for common errors
- Rate limiting for BASIC apps using a token bucket algorithm
- Secure password handling via Spring Security

---

## 🧪 Testing

Includes unit tests and service-layer logic coverage using:
- JUnit 5
- Mockito
- Spring Boot Starter Test

---

## 💡 Design Principles
- Clean Architecture with clear separation of concerns
- Domain-Driven Design elements
- DTO mapping to avoid leaking internal entities
- Token bucket logic isolated in a dedicated service
- Global exception handling via @ControllerAdvice

---

## 🗂️ Project Structure

```
FitnessTrackerAPI/
│
├── Fitness Tracker API/task/         # Main source code base
│   └── src/                          # Java source files organized by layers
│       ├── domain/                   # Core domain models and business logic
│       ├── application/              # Application services and use cases
│       ├── infrastructure/           # Persistence, API key auth, and repositories
│       └── interfaces/               # REST controllers (HTTP API layer)
│
├── Topics/                           # Educational materials, notes, or research
├── gradle/                           # Gradle wrapper and settings
├── build.gradle                      # Project dependencies and build config
└── README.md                         # Project documentation
```

This structure reflects **Clean Architecture**, ensuring that core domain logic is independent of external frameworks. It promotes testability and maintainability, which were key learning objectives of this project.
