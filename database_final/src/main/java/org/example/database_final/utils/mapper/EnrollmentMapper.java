package org.example.database_final.utils.mapper;

import org.example.database_final.dto.EnrollmentDto;
import org.example.database_final.entity.Enrollment;
import org.example.database_final.service.CourseService;
import org.example.database_final.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between `Enrollment` entities and `EnrollmentDto` data transfer objects (DTOs).
 * This class provides methods to transform enrollment data from the entity layer to the DTO layer and vice versa.
 * It uses services to fetch related entities for Course and Student for detailed mappings.
 */
@Component
public class EnrollmentMapper {
    private final CourseService courseService;
    private final StudentService studentService;

    /**
     * Constructor for `EnrollmentMapper`.
     *
     * @param courseService  The service to fetch course details.
     * @param studentService The service to fetch student details.
     */
    public EnrollmentMapper(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    /**
     * Converts an `Enrollment` entity to an `EnrollmentDto`.
     *
     * @param enrollment The `Enrollment` entity to be converted.
     * @return The corresponding `EnrollmentDto`.
     */
    public EnrollmentDto entityToDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setId(enrollment.getId());
        enrollmentDto.setEnrollmentDate(enrollment.getEnrollmentDate());
        enrollmentDto.setCompletionStatus(enrollment.getCompletionStatus());
        enrollmentDto.setCourseId(enrollment.getEnrollmentCourse().getId());
        enrollmentDto.setStudentName(enrollment.getEnrollmentStudent().getName());
        return enrollmentDto;
    }

    /**
     * Converts a list of `Enrollment` entities to a list of `EnrollmentDto` objects.
     *
     * @param enrollments The list of `Enrollment` entities to be converted.
     * @return A list of `EnrollmentDto` objects.
     */
    public List<EnrollmentDto> entityToDtoList(List<Enrollment> enrollments) {
        return enrollments.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    /**
     * Converts an `EnrollmentDto` to an `Enrollment` entity.
     *
     * @param enrollmentDto The `EnrollmentDto` to be converted.
     * @return The corresponding `Enrollment` entity.
     */
    public Enrollment dtoToEntity (EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(enrollmentDto.getId());
        enrollment.setEnrollmentDate(enrollmentDto.getEnrollmentDate());
        enrollment.setCompletionStatus(enrollmentDto.getCompletionStatus());
        enrollment.setEnrollmentCourse(courseService.getCourseById(enrollmentDto.getCourseId()));
        enrollment.setEnrollmentStudent(studentService.getStudentEntityByName(enrollmentDto.getStudentName()));
        return enrollment;
    }
}
