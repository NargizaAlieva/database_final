package org.example.database_final.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a Category entity in the database.
 * A category is used to group courses together based on their topics or areas of study.
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category.
     * This is a required field and cannot be null.
     */
    @Column(name = "name")
    private String name;

    /**
     * A detailed description of the category.
     */
    @Column(name = "description")
    private String description;

    /**
     * Default constructor for JPA
     */
    public Category() {}

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
