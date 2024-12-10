package org.example.database_final.utils.mapper;

import org.example.database_final.dto.EnrollmentDto;
import org.example.database_final.entity.Enrollment;
import org.example.database_final.service.CourseService;
import org.example.database_final.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnrollmentMapper {
    private CourseService courseService;
    private StudentService studentService;

    public EnrollmentMapper(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    public EnrollmentDto entityToDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setId(enrollment.getId());
        enrollmentDto.setEnrollmentDate(enrollment.getEnrollmentDate());
        enrollmentDto.setCompletionStatus(enrollment.getCompletionStatus());
        enrollmentDto.setCourseId(enrollment.getEnrollmentCourse().getId());
        enrollmentDto.setStudentName(enrollment.getEnrollmentStudent().getName());
        return enrollmentDto;
    }

    public List<EnrollmentDto> entityToDtoList(List<Enrollment> enrollments) {
        return enrollments.stream().map(this::entityToDto).collect(Collectors.toList());
    }

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
