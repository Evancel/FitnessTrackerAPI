# Fitness Tracker API

A Spring Boot REST API for managing fitness data, developer accounts, and client applications. This project demonstrates secure API design with authentication, rate limiting, and best practices for public APIs.

## Features

- Developer registration and authentication
- API key authentication for client applications
- Rate limiting for different client categories
- Upload and download fitness data
- Role-based access control
- Input validation and error handling

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JPA (Hibernate)
- Gradle

## Getting Started

### Prerequisites

- Java 17+
- Gradle

### Setup

1. Clone the repository:
git clone https://github.com/Evancel/FitnessTrackerAPI.git cd fitness-tracker-api
2. Build and run the application:
   ./gradlew bootRun
3. The API will be available at `http://localhost:8080`.

## API Endpoints

- `POST /api/developers/signup` — Register a new developer
- `GET /api/developers/{id}` — Get developer profile (authentication required)
- More endpoints for applications and fitness data (see source code)

## Documentation

Here's the link to the project: https://hyperskill.org/projects/408

## License

This project is for educational purposes.

---