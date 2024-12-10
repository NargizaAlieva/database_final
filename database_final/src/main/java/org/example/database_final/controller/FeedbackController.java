package org.example.database_final.controller;

import org.example.database_final.dto.FeedbackDto;
import org.example.database_final.dto.Response;
import org.example.database_final.service.FeedbackService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing feedbacks.
 */
@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    /**
     * Constructor for FeedbackController.
     *
     * @param feedbackService the service for handling feedback operations.
     */
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Retrieves feedbacks by course name.
     *
     * @param courseName the name of the course to retrieve feedback for.
     * @return a ResponseEntity containing a Response object with the feedback data or an error message.
     */
    @GetMapping(value = "/get-feedback-by-course-name/{courseName}")
    public ResponseEntity<Response> getFeedbackByCourseName(@PathVariable String courseName) {
        try {
            List<FeedbackDto> feedbacks = feedbackService.getFeedbackByCourseName(courseName);
            return ResponseEntity.ok(new Response("Successfully retrieved Feedback.", feedbacks));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Feedback. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves feedbacks by student name.
     *
     * @param studentName the name of the student to retrieve feedback for.
     * @return a ResponseEntity containing a Response object with the feedback data or an error message.
     */
    @GetMapping(value = "/get-feedback-by-student-name/{studentName}")
    public ResponseEntity<Response> getFeedbackByStudentName(@PathVariable String studentName) {
        try {
            List<FeedbackDto> feedbacks = feedbackService.getFeedbackByStudentName(studentName);
            return ResponseEntity.ok(new Response("Successfully retrieved Feedback.", feedbacks));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Feedback. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves all feedbacks.
     *
     * @return a ResponseEntity containing a Response object with a list of all feedbacks or an error message.
     */
    @GetMapping("/get-all-feedback")
    public ResponseEntity<Response> getAllFeedback() {
        try {
            List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
            return ResponseEntity.ok(new Response("Successfully retrieved all Feedbacks.", feedbacks));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find any Feedbacks. " + exception.getMessage(), null));
        }
    }

    /**
     * Creates a new feedback.
     *
     * @param request the DTO containing the new feedback data.
     * @return a ResponseEntity containing a Response object with the created feedback data or an error message.
     */
    @PostMapping(value = "/create-feedback")
    public ResponseEntity<Response> createFeedback(@RequestBody FeedbackDto request) {
        try {
            FeedbackDto feedbackDtoResponse = feedbackService.createFeedback(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Feedback.", feedbackDtoResponse));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Feedback could not be saved. " + exception.getMessage(), null));
        }
    }

    /**
     * Updates an existing feedback.
     *
     * @param request the DTO containing the updated feedback data.
     * @return a ResponseEntity containing a Response object with the updated feedback data or an error message.
     */
    @PutMapping(value = "/update-feedback")
    public ResponseEntity<Response> updateFeedback(@RequestBody FeedbackDto request) {
        try {
            FeedbackDto feedbackDtoResponse = feedbackService.updateFeedback(request);
            return ResponseEntity.ok(new Response("Updated Feedback successfully.", feedbackDtoResponse));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Feedback could not be updated. " + exception.getMessage(), null));
        }
    }

    /**
     * Deletes a feedback.
     *
     * @param feedbackId the ID of the feedback to delete.
     * @return a ResponseEntity containing a Response object indicating the success or failure of the deletion.
     */
    @DeleteMapping(value = "/delete-feedback/{feedbackId}")
    public ResponseEntity<Response> deleteFeedback(@PathVariable Long feedbackId) {
        try {
            feedbackService.deleteFeedback(feedbackId);
            return ResponseEntity.ok(new Response("Deleted Feedback successfully.", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Feedback could not be deleted. " + exception.getMessage(), null));
        }
    }

    /**
     * Sorts feedbacks by rating.
     *
     * @return a ResponseEntity containing a Response object with the sorted feedbacks or an error message.
     */
    @GetMapping(value = "/sort-by-rating")
    public ResponseEntity<Response> sortByRating() {
        try {
            List<FeedbackDto> sortedFeedbacks = feedbackService.sortByRating();
            return ResponseEntity.ok(new Response("Successfully retrieved sorted Feedbacks.", sortedFeedbacks));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve sorted Feedbacks. " + exception.getMessage(), null));
        }
    }

    /**
     * Sorts feedbacks by date.
     *
     * @return a ResponseEntity containing a Response object with the sorted feedbacks or an error message.
     */
    @GetMapping(value = "/sort-by-date")
    public ResponseEntity<Response> sortByDate() {
        try {
            List<FeedbackDto> sortedFeedbacks = feedbackService.sortByFeedbackDate();
            return ResponseEntity.ok(new Response("Successfully retrieved sorted Feedbacks.", sortedFeedbacks));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve sorted Feedbacks. " + exception.getMessage(), null));
        }
    }
}
