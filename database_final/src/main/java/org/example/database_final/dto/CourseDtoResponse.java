package org.example.database_final.dto;

import java.time.LocalDate;

public class CourseDtoResponse {
    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private Integer price;
    private String instructorName;
    private String categoryName;
    private Integer totalEnrollments;
    private Float averageRating;
    private LocalDate creationDate;

    public CourseDtoResponse() {}

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
