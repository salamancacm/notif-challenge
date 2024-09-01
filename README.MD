# Notification System

This repository contains a full-stack notification system with a Spring Boot backend and a React frontend for Gila Software Challenge. 

# Technology stack of this project
The stack used in this project is a modern and robust combination of technologies that allows for efficient development, testing, and deployment of both frontend and backend components. Here's a breakdown of the stack:

## Frontend:
- React.js
- Material-UI (MUI)
## Backend:
- Spring Boot
- Java 17
- Spring Data JPA
- H2 Database
## Development Tools:
- Maven
- npm (Node Package Manager)
## Testing:
- JUnit 5
- Mockito
## DevOps and Deployment:
- Concurrent Execution
- H2 Console

This stack combines a solid backend framework with a flexible frontend library, ensuring that the project is scalable, maintainable, and easy to develop and test.

Follow the instructions below to get both the backend and frontend running locally.

# Prerequisites
Before you begin, ensure you have the following installed on your machine:

- Java Development Kit (JDK) 17 or higher
- Node.js (v16 or higher) and npm (v8 or higher)
- Maven (for building the Spring Boot project)

# Setting Up the Backend (Spring Boot)
- Navigate to the backend directory:
```
cd backend
```
- Install dependencies and package the application (this step also run the tests):
```
mvn clean install
```
- Run the Spring Boot application:
```
mvn spring-boot:run
```

# Using the import.sql Script
The backend project contains an import.sql script located in the backend/src/main/resources directory. This script is automatically executed when the application starts and is used to pre-populate the H2 database with initial data.
- Purpose of import.sql: This script allows you to define SQL statements to insert initial data into the database tables. This can include test data, predefined configurations, or any necessary setup for the application to run correctly.

These statements will insert a user and a category into the respective tables when the application starts.

The backend will be available at http://localhost:8080.

# Accessing the H2 Database Console
Spring Boot provides an embedded H2 database for development and testing. You can access the H2 console to view and interact with the database directly.
- Ensure the application is running.
- Access the H2 console in your browser with the URL: http://localhost:8080/h2-console

Database connection details:

- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (leave blank)

Click "Connect" to access the database.

Use the H2 console to query the database, view tables, and manage the data.

# Setting Up the Frontend (React)
- Navigate to the frontend directory:
```
cd frontend
```
- Install the frontend dependencies:
```
npm install
```
- Start the React application:
```
npm start
```

The frontend will be available at http://localhost:3000.

# Running Backend and Frontend Simultaneously
To run both the backend and frontend concurrently, follow these steps:
- Navigate to the root directory of the repository (where this README.md is located).
- Install concurrently globally (if not installed):
```
npm install -g concurrently
```
- Make sure you already run **npm install** in ***/frontend*** folder
- Run both applications:
```
npm start
```
This command will start both the Spring Boot backend and the React frontend concurrently. The backend will run on http://localhost:8080 and the frontend on http://localhost:3000.