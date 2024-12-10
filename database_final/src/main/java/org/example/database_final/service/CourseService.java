package org.example.database_final.service;

import org.example.database_final.dto.CourseDtoRequest;
import org.example.database_final.dto.CourseDtoResponse;
import org.example.database_final.entity.Course;

import java.util.List;

/**
 * Service interface for managing `Course` entities.
 * This service provides methods for handling course data including CRUD operations and sorting functionalities.
 */
public interface CourseService {

    /**
     * Retrieves a `Course` by its unique ID.
     *
     * @param id The unique identifier of the course.
     * @return The `Course` entity corresponding to the provided ID.
     */
    Course getCourseById(Long id);

    /**
     * Retrieves a `Course` entity by its name.
     *
     * @param name The name of the course.
     * @return The `Course` entity corresponding to the provided name.
     */
    Course getCourseEntityByName(String name);

    /**
     * Retrieves a list of `CourseDtoResponse` objects by course name.
     *
     * @param name The name of the course to search for.
     * @return A list of `CourseDtoResponse` objects corresponding to the provided course name.
     */
    List<CourseDtoResponse> getCourseByName(String name);

    /**
     * Retrieves all courses available in the system.
     *
     * @return A list of `CourseDtoResponse` objects representing all courses.
     */
    List<CourseDtoResponse> getAllCourses();

    /**
     * Retrieves a list of `CourseDtoResponse` objects by category name.
     *
     * @param categoryName The name of the category to search for.
     * @return A list of `CourseDtoResponse` objects corresponding to the provided category name.
     */
    List<CourseDtoResponse> getCoursesByCategoryName(String categoryName);

    /**
     * Retrieves a list of `CourseDtoResponse` objects by instructor name.
     *
     * @param instructorName The name of the instructor to search for.
     * @return A list of `CourseDtoResponse` objects corresponding to the provided instructor name.
     */
    List<CourseDtoResponse> getCoursesByInstructorName(String instructorName);

    /**
     * Retrieves a list of `CourseDtoResponse` objects by student name.
     *
     * @param studentName The name of the student to search for.
     * @return A list of `CourseDtoResponse` objects corresponding to the provided student name.
     */
    List<CourseDtoResponse> getCoursesByStudentName(String studentName);

    /**
     * Creates a new course.
     *
     * @param CourseDto The data transfer object containing the details of the course to be created.
     * @return The created `CourseDtoResponse` object.
     */
    CourseDtoResponse createCourse(CourseDtoRequest CourseDto);

    /**
     * Updates an existing course.
     *
     * @param CourseDto The data transfer object containing the updated details of the course.
     * @return The updated `CourseDtoResponse` object.
     */
    CourseDtoResponse updateCourse(CourseDtoRequest CourseDto);

    /**
     * Sorts courses by duration.
     *
     * @return A list of `CourseDtoResponse` objects sorted by duration.
     */
    List<CourseDtoResponse> sortByDuration();

    /**
     * Sorts courses by price.
     *
     * @return A list of `CourseDtoResponse` objects sorted by price.
     */
    List<CourseDtoResponse> sortByPrice();

    /**
     * Sorts courses by the number of enrollments.
     *
     * @return A list of `CourseDtoResponse` objects sorted by enrollments.
     */
    List<CourseDtoResponse> sortByEnrollments();

    /**
     * Sorts courses by rating.
     *
     * @return A list of `CourseDtoResponse` objects sorted by rating.
     */
    List<CourseDtoResponse> sortByRating();
}
