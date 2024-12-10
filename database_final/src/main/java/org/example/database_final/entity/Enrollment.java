package org.example.database_final.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents an Enrollment entity in the database.
 * An enrollment links a student to a course, tracking the date of enrollment, completion status, and other relevant details.
 */
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The date when the student enrolled in the course.
     * This is automatically set to the current date when the entity is persisted.
     */
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    /**
     * The status of the enrollment, indicating if the student has completed the course.
     * This is a boolean field where `true` means completed and `false` means not completed.
     */
    @Column(name = "completion_status")
    private Boolean completionStatus;

    /**
     * The student who is enrolled in the course.
     * This is a many-to-one relationship mapped by `student_id`.
     */
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student enrollmentStudent;

    /**
     * The course in which the student is enrolled.
     * This is a many-to-one relationship mapped by `course_id`.
     */
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course enrollmentCourse;

    /**
     * Default constructor for JPA
     */
    public Enrollment() {}

    /**
     * This method is called before persisting the entity to set the enrollment date.
     */
    @PrePersist
    private void prePersist() {
        enrollmentDate = LocalDate.now();
    }

    /**
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public Enrollment setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public Enrollment setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
        return this;
    }

    public Boolean getCompletionStatus() {
        return completionStatus;
    }

    public Enrollment setCompletionStatus(Boolean completionStatus) {
        this.completionStatus = completionStatus;
        return this;
    }

    public Student getEnrollmentStudent() {
        return enrollmentStudent;
    }

    public Enrollment setEnrollmentStudent(Student enrollmentStudent) {
        this.enrollmentStudent = enrollmentStudent;
        return this;
    }

    public Course getEnrollmentCourse() {
        return enrollmentCourse;
    }

    public Enrollment setEnrollmentCourse(Course enrollmentCourse) {
        this.enrollmentCourse = enrollmentCourse;
        return this;
    }
}
