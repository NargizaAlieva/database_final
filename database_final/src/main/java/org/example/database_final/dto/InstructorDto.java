package org.example.database_final.dto;

import java.time.LocalDate;

public class InstructorDto {
    private Long id;
    private String name;
    private String email;
    private String bio;
    private LocalDate registrationDate;

    public InstructorDto() {}

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
