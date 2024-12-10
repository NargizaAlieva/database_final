package org.example.database_final.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents a Student entity in the database.
 * This entity stores information about students, including their personal details and registration date.
 */
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the student.
     * This is a required field and cannot be null.
     */
    @Column(name = "name")
    private String name;

    /**
     * The email address of the student.
     * This is a required field and must be a valid email format.
     */
    @Column(name = "email")
    private String email;

    /**
     * The date of birth of the student.
     * This should be a valid date and is used to calculate the student's age.
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * The date when the student registered.
     * This will be automatically set to the current date when the entity is persisted.
     */
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    /**
     * Default constructor for JPA.
     */
    public Student() {}

    /**
     * This method is called before persisting the entity to set the registration date.
     */
    @PrePersist
    private void prePersist() {
        registrationDate = LocalDate.now();
    }

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Student setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Student setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
