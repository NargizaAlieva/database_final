package org.example.database_final.service;

import org.example.database_final.dto.EnrollmentDto;
import org.example.database_final.entity.Enrollment;

import java.util.List;

/**
 * Service interface for managing `Enrollment` entities.
 * This service provides methods for handling enrollment data including CRUD operations.
 */
public interface EnrollmentService {

    /**
     * Retrieves an `Enrollment` by its unique ID.
     *
     * @param id The unique identifier of the enrollment.
     * @return The `Enrollment` entity corresponding to the provided ID.
     */
    Enrollment getEnrollmentById(Long id);

    /**
     * Retrieves a list of `EnrollmentDto` objects by course ID.
     *
     * @param courseId The unique identifier of the course.
     * @return A list of `EnrollmentDto` objects for the specified course.
     */
    List<EnrollmentDto> getEnrollmentByCourseId(Long courseId);

    /**
     * Retrieves a list of `EnrollmentDto` objects by student ID.
     *
     * @param studentId The unique identifier of the student.
     * @return A list of `EnrollmentDto` objects for the specified student.
     */
    List<EnrollmentDto> getEnrollmentByStudentId(Long studentId);

    /**
     * Retrieves all enrollments available in the system.
     *
     * @return A list of `EnrollmentDto` objects representing all enrollments.
     */
    List<EnrollmentDto> getAllEnrollments();

    /**
     * Creates a new enrollment.
     *
     * @param enrollmentDtoRequest The data transfer object containing the details of the enrollment to be created.
     * @return The created `EnrollmentDto` object.
     */
    EnrollmentDto createEnrollment(EnrollmentDto enrollmentDtoRequest);

    /**
     * Deletes an enrollment by course ID and student name.
     *
     * @param courseId     The unique identifier of the course.
     * @param studentName  The name of the student.
     */
    void deleteEnrollment(Long courseId, String studentName);

    /**
     * Sorts enrollments by enrollment date.
     *
     * @param enrollmentDtoList A list of `EnrollmentDto` objects to be sorted.
     * @return A list of `EnrollmentDto` objects sorted by enrollment date.
     */
    List<EnrollmentDto> sortByEnrollmentDate(List<EnrollmentDto> enrollmentDtoList);

    /**
     * Sorts enrollments by completion status.
     *
     * @param completionStatus The completion status to filter by.
     * @param enrollmentDtoList A list of `EnrollmentDto` objects to be sorted.
     * @return A list of `EnrollmentDto` objects sorted by completion status.
     */
    List<EnrollmentDto> sortByCompletionStatus(Boolean completionStatus, List<EnrollmentDto> enrollmentDtoList);
}
