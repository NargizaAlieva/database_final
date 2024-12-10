package org.example.database_final.dto;

public class CourseDtoRequest {
    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private Integer price;
    private String instructorName;
    private String categoryName;

    public CourseDtoRequest() {}

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
