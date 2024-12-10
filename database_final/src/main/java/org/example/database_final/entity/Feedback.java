package org.example.database_final.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents a Feedback entity in the database.
 * This entity stores feedback provided by a student for a course, including a rating, comment, and the date of the feedback.
 */
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The rating given by the student for the course.
     * This is a double value ranging from 0.0 to 5.0.
     */
    @Column(name = "rating")
    private Double rating;

    /**
     * The comment provided by the student for the course.
     */
    @Column(name = "comment")
    private String comment;

    /**
     * The date when the feedback was provided.
     * This will be automatically set to the current date when the entity is persisted.
     */
    @Column(name = "feedback_date")
    private LocalDate feedbackDate;

    /**
     * The student who provided the feedback.
     * This is a many-to-one relationship mapped by `student_id`.
     */
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student feedbackStudent;

    /**
     * The course for which the feedback was provided.
     * This is a many-to-one relationship mapped by `course_id`.
     */
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course feedbackCourse;

    /**
     * Default constructor for JPA
     */
    public Feedback() {
    }

    /**
     * This method is called before persisting the entity to set the feedback date.
     */
    @PrePersist
    private void prePersist() {
        feedbackDate = LocalDate.now();
    }

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public Feedback setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Feedback setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Feedback setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public Feedback setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
        return this;
    }

    public Student getFeedbackStudent() {
        return feedbackStudent;
    }

    public Feedback setFeedbackStudent(Student feedbackStudent) {
        this.feedbackStudent = feedbackStudent;
        return this;
    }

    public Course getFeedbackCourse() {
        return feedbackCourse;
    }

    public Feedback setFeedbackCourse(Course feedbackCourse) {
        this.feedbackCourse = feedbackCourse;
        return this;
    }
}
