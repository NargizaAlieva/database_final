package org.example.database_final.dto;

import java.time.LocalDate;

/**
 * A Data Transfer Object (DTO) class for transferring `Student` data between different layers of the application.
 * This class encapsulates the essential properties of a `Student` entity, providing a simplified view
 * for use in service and presentation layers. The `StudentDto` class helps in reducing the exposure of
 * the entity model and provides an abstraction layer for communication between the backend and frontend.
 */
public class StudentDto {
    /**
     * The unique identifier for the student. This ID is used to uniquely
     * identify a student object within the database.
     */
    private Long id;

    /**
     * The name of the student. This field contains the full name of the student.
     */
    private String name;

    /**
     * The email address of the student. It serves as a contact for communication.
     */
    private String email;

    /**
     * The date of birth of the student. This field captures the student's date of birth.
     */
    private LocalDate dateOfBirth;

    /**
     * The registration date of the student. It records when the student was registered in the system.
     */
    private LocalDate registrationDate;

    /**
     * Default constructor.
     * Initializes a new instance of `StudentDto`.
     */
    public StudentDto() {}

    /**
     * Getters and setters
     */

    public Long getId() {
        return id;
    }

    public StudentDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StudentDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public StudentDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public StudentDto setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
