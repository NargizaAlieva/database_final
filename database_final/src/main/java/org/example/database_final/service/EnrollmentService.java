package org.example.database_final.service;


import org.example.database_final.dto.EnrollmentDto;
import org.example.database_final.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Enrollment getEnrollmentById(Long id);
    List<EnrollmentDto> getEnrollmentByCourseId(Long courseId);
    List<EnrollmentDto> getEnrollmentByStudentId(Long studentId);
    List<EnrollmentDto> getAllEnrollments();

    EnrollmentDto createEnrollment(EnrollmentDto enrollmentDtoRequest);
    void deleteEnrollment(Long courseId,String studentName);

    List<EnrollmentDto> sortByEnrollmentDate(List<EnrollmentDto> enrollmentDtoList);
    List<EnrollmentDto> sortByCompletionStatus(Boolean completionStatus, List<EnrollmentDto> enrollmentDtoList);
}
