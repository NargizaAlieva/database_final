package org.example.database_final.dto;

/**
 * A Data Transfer Object (DTO) class designed to handle course data as a request between different layers of the application.
 * This class encapsulates all the necessary fields required to create or update a course, simplifying the data exchange between
 * service, controller, and repository layers. It is intended to be used in forms or APIs where course details are needed in
 * an input format.
 */
public class CourseDtoRequest {
    /**
     * The unique identifier for the course. It is used to identify the course in the database.
     */
    private Long id;

    /**
     * The title of the course. This is a brief and descriptive name for the course.
     */
    private String title;

    /**
     * A detailed description of the course content, objectives, and topics covered.
     */
    private String description;

    /**
     * The duration of the course in hours. This represents how long it will take to complete the course.
     */
    private Integer duration;

    /**
     *  The price of the course. This is typically set in terms of com.
     */
    private Integer price;

    /**
     *  The name of the instructor who teaches the course.
     */
    private String instructorName;

    /**
     * The name of the category to which the course belongs.
     */
    private String categoryName;

    /**
     * Default constructor.
     * Initializes a new instance of `CourseDtoRequest`.
     */
    public CourseDtoRequest() {}

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public CourseDtoRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseDtoRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDtoRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public CourseDtoRequest setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public CourseDtoRequest setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public CourseDtoRequest setInstructorName(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CourseDtoRequest setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
