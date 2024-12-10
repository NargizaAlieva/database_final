package org.example.database_final.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class FeedbackDto {
    private Long id;
    private Double rating;
    private String comment;
    private LocalDate feedbackDate;
    private String studentName;
    private Long courseId;
    private String courseName;

    public FeedbackDto() {}

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
