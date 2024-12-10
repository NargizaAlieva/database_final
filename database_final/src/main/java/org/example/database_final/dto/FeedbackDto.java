package org.example.database_final.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * A Data Transfer Object (DTO) class for transferring `Feedback` data between different layers of the application.
 * This class encapsulates the essential properties of a `Feedback` entity, providing a simplified view
 * for use in service and presentation layers. The `FeedbackDto` class helps in reducing the exposure of
 * the entity model and provides an abstraction layer for communication between the backend and frontend.
 */
public class FeedbackDto {
    /**
     * The unique identifier for the feedback.
     * This ID is used to uniquely identify a feedback object within the database.
     */
    private Long id;

    /**
     * The rating provided in the feedback.
     * It captures the score given by the student, typically in a range such as 1 to 5.
     */
    private Double rating;

    /**
     * The comment provided in the feedback.
     * It gives the detailed opinion or observations made by the student regarding the course.
     */
    private String comment;

    /**
     * The date when the feedback was given.
     * It captures the exact date and time when the feedback was recorded.
     */
    private LocalDate feedbackDate;

    /**
     * The name of the student who provided the feedback.
     * It links the feedback to the student who gave it.
     */
    private String studentName;

    /**
     * The ID of the course to which this feedback relates.
     * It provides a link between the feedback and the course being evaluated.
     */
    private Long courseId;

    /**
     * The name of the course to which this feedback relates.
     * It provides a textual representation of the course associated with this feedback.
     */
    private String courseName;

    /**
     * Default constructor.
     * Initializes a new instance of `FeedbackDto`.
     */
    public FeedbackDto() {}

    /**
     * Getters and setters
     */

    public Long getId() {
        return id;
    }

    public FeedbackDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public FeedbackDto setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public FeedbackDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public FeedbackDto setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public FeedbackDto setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Long getCourseId() {
        return courseId;
    }

    public FeedbackDto setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public FeedbackDto setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }
}
