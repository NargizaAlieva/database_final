package org.example.database_final.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an Instructor entity in the database.
 * An instructor is responsible for teaching courses and has personal details and a registration date.
 */
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the instructor.
     * This is a required field and cannot be null.
     */
    @Column(name = "name")
    private String name;

    /**
     * The email of the instructor.
     * This is a required field and should be unique.
     */
    @Column(name = "email")
    private String email;

    /**
     * A brief biography of the instructor.
     */
    @Column(name = "bio")
    private String bio;

    /**
     * The date when the instructor registered.
     * This will be automatically set to the current date when the entity is persisted.
     */
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    /**
     * Default constructor for JPA
     */
    public Instructor() {}

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

    public Instructor setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Instructor setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Instructor setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public Instructor setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
