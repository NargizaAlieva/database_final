package org.example.database_final.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "feedback_date")
    private Timestamp feedbackDate;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student feedbackStudent;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course feedbackCourse;

    public Feedback() {
    }

    @PrePersist
    private void prePersist() {
        feedbackDate = Timestamp.from(Instant.now());
    }

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

    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public Feedback setFeedbackDate(Timestamp feedbackDate) {
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

