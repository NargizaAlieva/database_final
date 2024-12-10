package org.example.database_final.dto;

import java.time.LocalDate;

public class EnrollmentDto {
    private Long id;
    private LocalDate enrollmentDate;
    private Boolean completionStatus;
    private String studentName;
    private Long courseId;

    public EnrollmentDto() {}

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
