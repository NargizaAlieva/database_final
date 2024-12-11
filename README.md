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
8. [Security Implementation](#8-security-implementation)
9. [Testing and Validation](#9-testing-and-validation)
10. [Conclusion](#10-conclusion)

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



