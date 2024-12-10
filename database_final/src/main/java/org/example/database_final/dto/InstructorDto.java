package org.example.database_final.dto;

import java.time.LocalDate;

/**
 * A Data Transfer Object (DTO) class for transferring instructor data between different layers of the application.
 * This class encapsulates the essential properties of an instructor, providing a simplified view for use in service
 * and presentation layers. The `InstructorDto` class helps in reducing the exposure of the entity model and provides
 * an abstraction layer for communication between the backend and frontend.
 */
public class InstructorDto {
    /**
     * The unique identifier of the instructor.
     */
    private Long id;

    /**
     * The name of the instructor.
     */
    private String name;

    /**
     * The email address of the instructor.
     */
    private String email;

    /**
     * A brief biography or description of the instructor.
     */
    private String bio;

    /**
     * The registration date of the instructor.
     */
    private LocalDate registrationDate;

    /**
     * Default constructor.
     * Initializes a new instance of `InstructorDto`.
     */
    public InstructorDto() {}

    /**
     * Getters and setters
     */

    public Long getId() {
        return id;
    }

    public InstructorDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public InstructorDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InstructorDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public InstructorDto setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public InstructorDto setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
