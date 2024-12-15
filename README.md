# Brain Rush Project Documentation

**Team Brain Rush**  
**December 11, 2024**

## Abstract
This document provides an overview of the Brain Rush project, covering the system design, database structure, backend (Java Spring Boot), frontend (Thymeleaf), code snippets, usage instructions, and system setup.

## Contents
1. [Project Overview](#1-project-overview)
2. [System Architecture](#2-system-architecture)
3. [Conceptual Design](#3-conceptual-design)
4. [Logical Design](#4-logical-design)
5. [Database Setup](#5-database-setup)
6. [Backend Implementation (Java Spring Boot)](#6-backend-implementation-java-spring-boot)
7. [Frontend Implementation (Thymeleaf)](#7-frontend-implementation-thymeleaf)
8. [How to run our project](#8-how-to-run-our-project)
9. [Maintanse project](#9-maintanse-project)

## 1. Project Overview
### 1.1 Title:
Brain Rush

### 1.2 Objective:
To create a platform that manages online courses, students, instructors, enrollments, and feedback, with advanced analytics. Built with a Java Spring Boot backend and a Thymeleaf frontend.

## 2. System Architecture
### 2.1 Technologies Used:
- Backend: Java with Spring Boot (Spring MVC, Spring Data JPA)
- Frontend: Thymeleaf templates
- Database: PostgreSQL
- API: RESTful services

## 3. Conceptual Design
### 3.1 Entities and Attributes:
- **Course:** ID (PK), Title, Description, Duration, Price, Category, InstructorID (FK), TotalEnrollments (calculated), AverageRating (calculated)
- **Student:** ID (PK), Name, Email, Age, Address, RegistrationDate
- **Instructor:** ID (PK), Name, Email, Bio, Rating (calculated)
- **Enrollment:** ID (PK), StudentID (FK), CourseID (FK), EnrollmentDate, CompletionStatus
- **Feedback:** ID (PK), StudentID (FK), CourseID (FK), Rating, Comment, FeedbackDate
- **Category:** ID (PK), Name, Description

### 3.2 Relationships:
- **Course-Instructor:** One-to-Many
- **Student-Course:** Many-to-Many via Enrollment
- **Student-Feedback:** One-to-Many
- **Course-Feedback:** One-to-Many
- **Course-Category:** Many-to-One

## 4. Logical Design
### 4.1 Database Tables:
- Course, Student, Instructor, Enrollment, Feedback, Category

### 4.2 Primary and Foreign Keys:
- Primary Keys: Unique IDs for each entity
- Foreign Keys: Keys that link entities

### 4.3 Junction Tables:
- Student-Course: Handles many-to-many relationship between students and courses

### 4.4 Normalization:
- Third Normal Form (3NF) is applied for minimal redundancy and optimal performance.

## 5. Database Setup
### 5.1 Database Management System:
- PostgreSQL

### 5.2 SQL Scripts:
#### DDL Scripts:
```sql
CREATE DATABASE brain_rush;
CREATE TABLE IF NOT EXISTS Instructor (
    ID BIGSERIAL PRIMARY KEY,
    Name VARCHAR(100) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Bio TEXT,
    Registration_Date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS Category (
    ID BIGSERIAL PRIMARY KEY,
    Name VARCHAR(100) UNIQUE NOT NULL,
    Description TEXT
);

CREATE TABLE IF NOT EXISTS Course (
    ID BIGSERIAL PRIMARY KEY,
    Title VARCHAR(200) NOT NULL,
    Description TEXT,
    Duration INT NOT NULL,
    Price INT NOT NULL,
    Instructor_ID BIGINT REFERENCES Instructor(ID) ON DELETE SET NULL,
    Category_ID BIGINT REFERENCES Category(ID) ON DELETE SET NULL,
    Total_Enrollments INT DEFAULT 0 CHECK (Total_Enrollments >= 0),
    Average_Rating FLOAT DEFAULT 0.0 CHECK (Average_Rating BETWEEN 0 AND 5),
    Creation_Date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS Student (
    ID BIGSERIAL PRIMARY KEY,
    Name VARCHAR(100) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Date_Of_Birth DATE NOT NULL,
    Registration_Date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS Enrollment (
    ID BIGSERIAL PRIMARY KEY,
    Student_ID BIGINT REFERENCES Student(ID) ON DELETE CASCADE,
    Course_ID BIGINT REFERENCES Course(ID) ON DELETE CASCADE,
    Enrollment_Date DATE DEFAULT CURRENT_DATE,
    Completion_Status BOOLEAN DEFAULT FALSE,
    UNIQUE (Student_ID, Course_ID)
);

CREATE TABLE IF NOT EXISTS Feedback (
    ID BIGSERIAL PRIMARY KEY,
    Student_ID BIGINT REFERENCES Student(ID) ON DELETE CASCADE,
    Course_ID BIGINT REFERENCES Course(ID) ON DELETE CASCADE,
    Rating FLOAT NOT NULL CHECK (Rating BETWEEN 0 AND 5),
    Comment TEXT,
    Feedback_Date DATE DEFAULT CURRENT_DATE
);

## 8. How to run our project 
### 8.1 Prerequisites
    - 1. Download PostgreSQL 16:
        - Visit the PostgreSQL download page.
        - Select the appropriate installer for your operating system (Windows, macOS, or Linux).
        - Install PostgreSQL 16 by following the on-screen instructions.
    - 2. Set Up PostgreSQL Database:
        - Open SQL PowerShell or your preferred PostgreSQL client (like pgAdmin).
        - Create a new database named brain_rush:
        - CREATE DATABASE brain_rush;
### 7.1 Running the Brain Rush Project with Flyway
    - 1. Open the Project:
        - Open your favorite Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or Visual Studio Code.
        - Import or open the Brain Rush project in your IDE.
    - 2. Configure Database Connection:
        - Locate the application.properties file (usually under src/main/resources).
        - Modify the database connection properties to match your PostgreSQL setup:
            spring.datasource.url=jdbc:postgresql://localhost:5432/brain_rush
            spring.datasource.username=your_username
            spring.datasource.password=your_password
            spring.datasource.driver-class-name=org.postgresql.Driver
            flyway.locations=classpath:/db/migration
    - 3. This configuration tells Flyway to look for migration scripts in the             src/main/resources/db/migration directory.
    - 4. Run the Project:
        In your IDE, locate the main class of the Brain Rush project.
        Run the project as a Java application.
        The server should start. During startup, Flyway will automatically run any pending database migrations based on the scripts found in the db/migration folder.
    - 5. Verify Migrations:
        - You can verify the migrations have been applied by checking the Flyway status:
            ./mvnw flyway:info
        - If you want to apply migrations manually or check the applied migrations:
            ./mvnw flyway:migrate
    - 6. Testing:
        Go to this link http://localhost:8888/course

    - 7. Handling Issues:
        - If you encounter any issues with the database connection, ensure that your PostgreSQL server is running and that your user has the appropriate permissions.
        - Verify that the database brain_rush exists and that the connection details in application.properties are correct.
        - Check the logs for any Flyway-related errors to diagnose issues with migration scripts or database access.
## 9. Maintanse project
### 9.1 Database Maintenance
    - 1. Regular Backups:
        - Use pg_dump to back up your database regularly (daily recommended).
            pg_dump -U your_username -d brain_rush -F c -b -v -f /path/to/backup/brain_rush_backup.sql
    - 2. Optimize Database Performance:
        - Regularly run VACUUM FULL; and ANALYZE; in SQL.
        - Set PostgreSQL’s auto_vacuum setting to on:
            ALTER DATABASE brain_rush SET auto_vacuum = on;
    - 3. Monitor Database Health:
        - Check disk space and review logs for errors.
        - Use:
            df -h
            tail -f /var/log/postgresql/postgresql-16-main.log
    - 4. Flyway Migrations:
        - Check for pending migrations:
            ./mvnw flyway:info
        - Apply migrations:
            ./mvnw flyway:migrate
### 9.2 Application Maintenance
    - 1. Update Dependencies:
        - Check for updates:
            ./mvnw versions:display-dependency-updates
        - Update dependencies:
            ./mvnw versions:use-latest-releases
    - 2. Log Monitoring:
        - Use logback for structured logging:
            tail -f /path/to/logs/application.log
    - 3. Health Checks:
        - Expose a health check endpoint:
            GET http://localhost:8080/actuator/health

    - 4. Regular Restarts:
        - Periodically restart the application server:
            ./mvnw spring-boot:stop
            ./mvnw spring-boot:start
    - 5. Backup Configuration Files:
        - Regularly back up application configuration files:
            cp /path/to/config /path/to/backup/

By following these routine maintenance tasks, the Brain Rush project will run efficiently and remain secure.



