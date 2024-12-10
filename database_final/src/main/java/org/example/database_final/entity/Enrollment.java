package org.example.database_final.entity;



import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "completion_status")
    private Boolean completionStatus;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student enrollmentStudent;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course enrollmentCourse;

    public Enrollment() {}

    @PrePersist
    private void prePersist() {
        enrollmentDate = LocalDate.now();
    }

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

