package org.example.database_final.controller;

import org.example.database_final.dto.EnrollmentDto;
import org.example.database_final.dto.Response;
import org.example.database_final.service.EnrollmentService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing enrollments.
 */
@RestController
@RequestMapping(value = "/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    /**
     * Constructor for EnrollmentController.
     *
     * @param enrollmentService the service for handling enrollment operations.
     */
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * Retrieves enrollments by course ID.
     *
     * @param courseId the ID of the course to retrieve enrollments for.
     * @return a ResponseEntity containing a Response object with the enrollment data or an error message.
     */
    @GetMapping(value = "/get-enrollment-by-course-id/{courseId}")
    public ResponseEntity<Response> getEnrollmentByCourseId(@PathVariable Long courseId) {
        try {
            List<EnrollmentDto> enrollments = enrollmentService.getEnrollmentByCourseId(courseId);
            return ResponseEntity.ok(new Response("Successfully retrieved Enrollment.", enrollments));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Enrollment. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves enrollments by student ID.
     *
     * @param studentId the ID of the student to retrieve enrollments for.
     * @return a ResponseEntity containing a Response object with the enrollment data or an error message.
     */
    @GetMapping(value = "/get-enrollment-by-student-id/{studentId}")
    public ResponseEntity<Response> getEnrollmentByStudentId(@PathVariable Long studentId) {
        try {
            List<EnrollmentDto> enrollments = enrollmentService.getEnrollmentByStudentId(studentId);
            return ResponseEntity.ok(new Response("Successfully retrieved Enrollment.", enrollments));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Enrollment. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves all enrollments.
     *
     * @return a ResponseEntity containing a Response object with a list of all enrollments or an error message.
     */
    @GetMapping("/get-all-enrollment")
    public ResponseEntity<Response> getAllEnrollment() {
        try {
            List<EnrollmentDto> enrollments = enrollmentService.getAllEnrollments();
            return ResponseEntity.ok(new Response("Successfully retrieved all Enrollments.", enrollments));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find any Enrollments. " + exception.getMessage(), null));
        }
    }

    /**
     * Creates a new enrollment.
     *
     * @param request the DTO containing the new enrollment data.
     * @return a ResponseEntity containing a Response object with the created enrollment data or an error message.
     */
    @PostMapping(value = "/create-enrollment")
    public ResponseEntity<Response> createEnrollment(@RequestBody EnrollmentDto request) {
        try {
            EnrollmentDto enrollmentDto = enrollmentService.createEnrollment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Enrollment.", enrollmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Enrollment could not be saved. " + exception.getMessage(), null));
        }
    }

    /**
     * Deletes an enrollment.
     *
     * @param courseId    the ID of the course to delete enrollment for.
     * @param studentName the name of the student to delete enrollment for.
     * @return a ResponseEntity containing a Response object indicating the success or failure of the deletion.
     */
    @DeleteMapping(value = "/delete-enrollment/{courseId}/{studentName}")
    public ResponseEntity<Response> deleteEnrollment(@PathVariable Long courseId, @PathVariable String studentName) {
        try {
            enrollmentService.deleteEnrollment(courseId, studentName);
            return ResponseEntity.ok(new Response("Deleted Enrollment successfully.", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Enrollment could not be deleted. " + exception.getMessage(), null));
        }
    }

    /**
     * Sorts enrollments by enrollment date.
     *
     * @param enrollmentDtoList the list of EnrollmentDto objects to sort.
     * @return a ResponseEntity containing a Response object with the sorted enrollments or an error message.
     */
    @GetMapping(value = "/sort-by-date")
    public ResponseEntity<Response> sortByEnrollmentDate(@RequestBody List<EnrollmentDto> enrollmentDtoList) {
        try {
            List<EnrollmentDto> sortedEnrollments = enrollmentService.sortByEnrollmentDate(enrollmentDtoList);
            return ResponseEntity.ok(new Response("Successfully retrieved sorted Enrollments.", sortedEnrollments));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve sorted Enrollments. " + exception.getMessage(), null));
        }
    }

    /**
     * Sorts enrollments by completion status.
     *
     * @param status               the completion status to filter enrollments.
     * @param enrollmentDtoList the list of EnrollmentDto objects to sort.
     * @return a ResponseEntity containing a Response object with the sorted enrollments or an error message.
     */
    @GetMapping(value = "/sort-by-completion-status")
    public ResponseEntity<Response> sortByCompletionStatus(@RequestParam Boolean status, @RequestBody List<EnrollmentDto> enrollmentDtoList) {
        try {
            List<EnrollmentDto> sortedEnrollments = enrollmentService.sortByCompletionStatus(status, enrollmentDtoList);
            return ResponseEntity.ok(new Response("Successfully retrieved sorted Enrollments.", sortedEnrollments));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve sorted Enrollments. " + exception.getMessage(), null));
        }
    }
}
