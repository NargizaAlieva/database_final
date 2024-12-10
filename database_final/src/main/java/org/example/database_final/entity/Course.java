package org.example.database_final.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents a Course entity in the database.
 * A course is defined by its title, description, duration, price, and other attributes.
 */
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the course.
     * This is a required field and cannot be null.
     */
    @Column(name = "title")
    private String title;

    /**
     * A detailed description of the course.
     */
    @Column(name = "description")
    private String description;

    /**
     * The duration of the course in hours.
     * This is a required field and should be a positive integer.
     */
    @Column(name = "duration")
    private Integer duration;

    /**
     * The price of the course.
     * This is a required field and should be a positive integer.
     */
    @Column(name = "price")
    private Integer price;

    /**
     * The total number of enrollments for the course.
     */
    @Column(name = "total_enrollments")
    private Integer totalEnrollments;

    /**
     * The average rating given by students for the course.
     * This is a floating-point value.
     */
    @Column(name = "average_rating")
    private Float averageRating;

    /**
     * The date when the course was created.
     * This will be automatically set to the current date when the entity is persisted.
     */
    @Column(name = "creation_date")
    private LocalDate creationDate;

    /**
     * The instructor who teaches this course.
     * This is a many-to-one relationship mapped by `instructor_id`.
     */
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor courseInstructor;

    /**
     * The category to which this course belongs.
     * This is a many-to-one relationship mapped by `category_id`.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category courseCategory;

    /**
     * Default constructor for JPA
     */
    public Course() {}

    /**
     * This method is called before persisting the entity to set the creation date.
     */
    @PrePersist
    private void prePersist() {
        creationDate = LocalDate.now();
    }

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Course setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Course setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getTotalEnrollments() {
        return totalEnrollments;
    }

    public Course setTotalEnrollments(Integer totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
        return this;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public Course setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Course setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Instructor getInstructor() {
        return courseInstructor;
    }

    public Course setInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
        return this;
    }

    public Category getCategory() {
        return courseCategory;
    }

    public Course setCategory(Category courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    public Course setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
        return this;
    }

    public Category getCourseCategory() {
        return courseCategory;
    }

    public Course setCourseCategory(Category courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }
}
