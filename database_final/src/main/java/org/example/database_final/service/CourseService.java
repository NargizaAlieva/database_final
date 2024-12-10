package org.example.database_final.service;

import org.example.database_final.dto.CourseDtoRequest;
import org.example.database_final.dto.CourseDtoResponse;
import org.example.database_final.entity.Course;

import java.util.List;

public interface CourseService {
    Course getCourseById(Long id);
    Course getCourseEntityByName(String name);
    List<CourseDtoResponse> getCourseByName(String name);
    List<CourseDtoResponse> getAllCourses();
    List<CourseDtoResponse> getCoursesByCategoryName(String categoryName);
    List<CourseDtoResponse> getCoursesByInstructorName(String instructorName);
    List<CourseDtoResponse> getCoursesByStudentName(String studentName);

    CourseDtoResponse createCourse(CourseDtoRequest CourseDto);
    CourseDtoResponse updateCourse(CourseDtoRequest CourseDto);

    List<CourseDtoResponse> sortByDuration();
    List<CourseDtoResponse> sortByPrice();
    List<CourseDtoResponse> sortByEnrollments();
    List<CourseDtoResponse> sortByRating();
}
