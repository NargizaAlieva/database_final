package org.example.database_final.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

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
    private LocalDate feedbackDate;

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
        feedbackDate = LocalDate.now();
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

