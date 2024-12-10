package org.example.database_final.controller;

import org.example.database_final.dto.*;
import org.example.database_final.service.CourseService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing courses.
 */
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    /**
     * Constructor for CourseController.
     *
     * @param courseService the service for handling course operations.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Retrieves a course by its name.
     *
     * @param courseName the name of the course to retrieve.
     * @return a ResponseEntity containing a Response object with the course data or an error message.
     */
    @GetMapping(value = "/get-course-by-name/{courseName}")
    public ResponseEntity<Response> getCourseByName(@PathVariable String courseName) {
        try {
            List<CourseDtoResponse> courseDtoResponses = courseService.getCourseByName(courseName);
            return ResponseEntity.ok(new Response("Successfully retrieved Course.", courseDtoResponses));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Course. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves all courses.
     *
     * @return a ResponseEntity containing a Response object with a list of all courses or an error message.
     */
    @GetMapping("/get-all-courses")
    public ResponseEntity<Response> getAllCourse() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved all Courses.", courseService.getAllCourses()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find any Courses. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves courses by category name.
     *
     * @param categoryName the name of the category.
     * @return a ResponseEntity containing a Response object with a list of courses for the given category or an error message.
     */
    @GetMapping(value = "/get-course-by-category-name/{categoryName}")
    public ResponseEntity<Response> getCourseByCategoryName(@PathVariable String categoryName) {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Courses.", courseService.getCoursesByCategoryName(categoryName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Courses. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves courses by instructor name.
     *
     * @param instructorName the name of the instructor.
     * @return a ResponseEntity containing a Response object with a list of courses for the given instructor or an error message.
     */
    @GetMapping(value = "/get-course-by-instructor-name/{instructorName}")
    public ResponseEntity<Response> getCourseByInstructorName(@PathVariable String instructorName) {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Courses.", courseService.getCoursesByInstructorName(instructorName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Courses. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves courses by student name.
     *
     * @param studentName the name of the student.
     * @return a ResponseEntity containing a Response object with a list of courses for the given student or an error message.
     */
    @GetMapping(value = "/get-course-by-student-name/{studentName}")
    public ResponseEntity<Response> getCourseByStudentName(@PathVariable String studentName) {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Courses.", courseService.getCoursesByStudentName(studentName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Courses. " + exception.getMessage(), null));
        }
    }

    /**
     * Creates a new course.
     *
     * @param request the DTO containing the new course data.
     * @return a ResponseEntity containing a Response object with the created course data or an error message.
     */
    @PostMapping(value = "/create-course")
    public ResponseEntity<Response> createCourse(@RequestBody CourseDtoRequest request) {
        try {
            CourseDtoResponse courseDtoResponse = courseService.createCourse(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Course.", courseDtoResponse));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Course could not be saved. " + exception.getMessage(), null));
        }
    }

    /**
     * Updates an existing course.
     *
     * @param request the DTO containing the updated course data.
     * @return a ResponseEntity containing a Response object with the updated course data or an error message.
     */
    @PutMapping(value = "/update-course")
    public ResponseEntity<Response> updateCourse(@RequestBody CourseDtoRequest request) {
        try {
            CourseDtoResponse courseDtoResponse = courseService.updateCourse(request);
            return ResponseEntity.ok(new Response("Updated Course successfully.", courseDtoResponse));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Course could not be updated. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves and sorts courses by duration.
     *
     * @return a ResponseEntity containing a Response object with a sorted list of courses by duration or an error message.
     */
    @GetMapping(value = "/sort-by-duration")
    public ResponseEntity<Response> sortByDuration() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Courses.", courseService.sortByDuration()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Courses. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves and sorts courses by price.
     *
     * @return a ResponseEntity containing a Response object with a sorted list of courses by price or an error message.
     */
    @GetMapping(value = "/sort-by-price")
    public ResponseEntity<Response> sortByPrice() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Courses.", courseService.sortByPrice()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Courses. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves and sorts courses by enrollments.
     *
     * @return a ResponseEntity containing a Response object with a sorted list of courses by enrollments or an error message.
     */
    @GetMapping(value = "/sort-by-enrollments")
    public ResponseEntity<Response> sortByEnrollments() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Courses.", courseService.sortByEnrollments()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Courses. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves and sorts courses by rating.
     *
     * @return a ResponseEntity containing a Response object with a sorted list of courses by rating or an error message.
     */
    @GetMapping(value = "/sort-by-rating")
    public ResponseEntity<Response> sortByRating() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Courses.", courseService.sortByRating()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Courses. " + exception.getMessage(), null));
        }
    }
}
