Game Backend API

A Spring Boot REST API for managing game players, scores, and a global leaderboard built with JWT authentication and secure password handling.


## Features

- Player management — create, read, update, and delete player profiles
- Score tracking — record game sessions and automatically maintain each player's high score
- Leaderboard — get players ranked by their highest score
- Authentication — JWT-based login system with password hashing
- Protected endpoints — score submission requires a valid JWT token
- Input validation — required fields are validated before reaching the database
- Centralized error handling — consistent, readable error responses for invalid requests

## Tech Stack

- Java 21
- Spring Boot (Web, Data JPA, Security, Validation)
- PostgreSQL — relational database
- JWT — token-based authentication
- Maven — dependency management

## Architecture

- **Controller** → handles HTTP requests/responses
- **Service** → contains business logic
- **Repository** → handles database access
- **Model** → JPA entities (Player, Score)
- **DTO** → shapes data returned to clients
- **Config** → security configuration and JWT filter
