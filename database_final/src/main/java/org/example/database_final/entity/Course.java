package org.example.database_final.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @Column(name = "total_enrollments")
    private Integer totalEnrollments;

    @Column(name = "average_rating")
    private Float averageRating;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor courseInstructor;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category courseCategory;

    @OneToMany(mappedBy = "feedbackCourse")
    List<Feedback> courseFeedbacks;

    @OneToMany(mappedBy = "enrollmentCourse")
    List<Enrollment> courseEnrollments;

    public Course() {}

    @PrePersist
    private void prePersist() {
        creationDate = LocalDate.now();
    }

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

    public List<Feedback> getCourseFeedbacks() {
        return courseFeedbacks;
    }

    public Course setCourseFeedbacks(List<Feedback> courseFeedbacks) {
        this.courseFeedbacks = courseFeedbacks;
        return this;
    }

    public List<Enrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public Course setCourseEnrollments(List<Enrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
        return this;
    }
}
