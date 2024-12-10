package org.example.database_final.dto;

import java.time.LocalDate;

/**
 * A Data Transfer Object (DTO) class for transferring detailed course information as a response between different layers of the application.
 * This class encapsulates all the necessary fields to represent a course, including its metadata such as total enrollments,
 * average rating, and creation date. It provides a comprehensive view of the course details for use in service and presentation layers.
 * The `CourseDtoResponse` class is useful for reducing the exposure of the entity model and providing a clear and concise data representation
 * for APIs and other client interfaces.
 */
public class CourseDtoResponse {
    /**
     * The unique identifier of the course.
     */
    private Long id;
    /**
     * The title of the course.
     */
    private String title;
    /**
     * A brief description of the course content, objectives, and topics covered.
     */
    private String description;
    /**
     * The duration of the course in hours.
     */
    private Integer duration;
    /**
     * The cost of the course.
     */
    private Integer price;
    /**
     * GThe name of the course instructor.
     */
    private String instructorName;
    /**
     * The name of the category associated with the course.
     */
    private String categoryName;
    /**
     * The total number of enrollments for the course.
     */
    private Integer totalEnrollments;
    /**
     * The average rating given by students who have completed the course.
     */
    private Float averageRating;
    /**
     *  The date when the course was created.
     */
    private LocalDate creationDate;

    /**
     * Default constructor.
     * Initializes a new instance of `CourseDtoResponse`.
     */
    public CourseDtoResponse() {}

    /**
     * Getters and setters
     */

    public Long getId() {
        return id;
    }

    public CourseDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public CourseDtoResponse setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public CourseDtoResponse setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public CourseDtoResponse setInstructorName(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CourseDtoResponse setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getTotalEnrollments() {
        return totalEnrollments;
    }

    public CourseDtoResponse setTotalEnrollments(Integer totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
        return this;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public CourseDtoResponse setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public CourseDtoResponse setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
