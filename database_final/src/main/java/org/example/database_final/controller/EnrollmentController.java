package org.example.database_final.controller;

import org.example.database_final.dto.*;
import org.example.database_final.service.EnrollmentService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping(value = "/get-enrollment-by-course-id/{courseId}")
    public ResponseEntity<Response> getFeedbackByCourseId(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Enrollment.", enrollmentService.getEnrollmentByCourseId(courseId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Enrollment. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-enrollment-by-student-id/{studentId}")
    public ResponseEntity<Response> getFeedbackByStudentId(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Enrollment.", enrollmentService.getEnrollmentByStudentId(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Enrollment. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-enrollment")
    public ResponseEntity<Response> getAllFeedback() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Enrollments.", enrollmentService.getAllEnrollments()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-enrollment")
    public ResponseEntity<Response> createFeedback(@RequestBody EnrollmentDto request) {
        try {
            EnrollmentDto enrollmentDto = enrollmentService.createEnrollment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Enrollment.", enrollmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Course is not saved. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/sort-by-date")
    public ResponseEntity<Response> sortByRating(@RequestBody List<EnrollmentDto> enrollmentDtoList) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Enrollments.", enrollmentService.sortByEnrollmentDate(enrollmentDtoList)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Enrollments. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/sort-by-completion-status")
    public ResponseEntity<Response> sortByCompletionStatus(@RequestParam Boolean status, @RequestBody List<EnrollmentDto> enrollmentDtoList) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Enrollments.", enrollmentService.sortByCompletionStatus(status, enrollmentDtoList)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Enrollments. " + exception.getMessage(), null));
        }
    }
}
