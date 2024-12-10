package org.example.database_final.dto;

import java.time.LocalDate;

/**
 * A Data Transfer Object (DTO) class for transferring `Enrollment` data between different layers of the application.
 * This class encapsulates the essential properties of an `Enrollment` entity, providing a simplified view
 * for use in service and presentation layers. The `EnrollmentDto` class helps in reducing the exposure of
 * the entity model and provides an abstraction layer for communication between the backend and frontend.
 */
public class EnrollmentDto {
    /**
     * The unique identifier for the enrollment.
     * This ID is used to uniquely identify an enrollment object within the database.
     */
    private Long id;

    /**
     * The date of enrollment.
     * It captures the date when a student enrolled in a course.
     */
    private LocalDate enrollmentDate;

    /**
     * The completion status of the enrollment.
     * Indicates whether the student has completed the course.
     */
    private Boolean completionStatus;

    /**
     * The name of the student enrolled in the course.
     * Provides a reference to the student related to this enrollment.
     */
    private String studentName;

    /**
     * The ID of the course the student is enrolled in.
     * Links to the course entity associated with this enrollment.
     */
    private Long courseId;

    /**
     * Default constructor.
     * Initializes a new instance of `EnrollmentDto`.
     */
    public EnrollmentDto() {}

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public EnrollmentDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public EnrollmentDto setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
        return this;
    }

    public Boolean getCompletionStatus() {
        return completionStatus;
    }

    public EnrollmentDto setCompletionStatus(Boolean completionStatus) {
        this.completionStatus = completionStatus;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public EnrollmentDto setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Long getCourseId() {
        return courseId;
    }

    public EnrollmentDto setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }
}
