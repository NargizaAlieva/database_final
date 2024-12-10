package org.example.database_final.dto;

import java.io.Serializable;

/**
 * A Data Transfer Object (DTO) class for transferring `Category` data between different layers of the application.
 * This class encapsulates the essential properties of a `Category` entity, providing a simplified view
 * for use in service and presentation layers. The `CategoryDto` class helps in reducing the exposure of
 * the entity model and provides an abstraction layer for communication between the backend and frontend.
 */
public class CategoryDto implements Serializable {

    /**
     * The unique identifier for the category. This ID is used to uniquely
     * identify a category object within the database.
     */
    private Long id;

    /**
     * The name of the category. It provides a textual representation for the category,
     * typically used for identification and labeling within the application.
     */
    private String name;

    /**
     * A brief description of the category. It offers additional context or details about
     * what the category represents. This field is optional and can be left empty.
     */
    private String description;

    /**
     * Default constructor.
     * Initializes a new instance of `CategoryDto`.
     */
    public CategoryDto() {}

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public CategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
