package org.example.database_final.controller;

import org.example.database_final.dto.*;
import org.example.database_final.service.FeedbackService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping(value = "/get-feedback-by-course-name/{courseName}")
    public ResponseEntity<Response> getFeedbackByCourseName(@PathVariable String courseName) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Feedback.", feedbackService.getFeedbackByCourseName(courseName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Feedback. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-feedback-by-student-name/{studentName}")
    public ResponseEntity<Response> getFeedbackByStudentName(@PathVariable String studentName) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Feedback.", feedbackService.getFeedbackByStudentName(studentName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Feedback. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-feedback")
    public ResponseEntity<Response> getAllFeedback() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Feedbacks.", feedbackService.getAllFeedbacks()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-feedback")
    public ResponseEntity<Response> createFeedback(@RequestBody FeedbackDto request) {
        try {
            FeedbackDto feedbackDtoResponse = feedbackService.createFeedback(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Feedback.", feedbackDtoResponse));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Feedback is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-feedback")
    public ResponseEntity<Response> updateFeedback(@RequestBody FeedbackDto request) {
        try {
            FeedbackDto feedbackDtoResponse = feedbackService.updateFeedback(request);
            return ResponseEntity.ok(new Response("Updated Feedback successfully.", feedbackDtoResponse));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Feedback is not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-feedback/{feedbackId}")
    public ResponseEntity<Response> deleteFeedback(@PathVariable Long feedbackId) {
        try {
            feedbackService.deleteFeedback(feedbackId);
            return ResponseEntity.ok(new Response("Deleted Feedback successfully.", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Feedback is not deleted. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/sort-by-rating")
    public ResponseEntity<Response> sortByRating() {
        try {
            return ResponseEntity.ok(new Response("Successfully get Feedbacks.", feedbackService.sortByRating()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Feedbacks. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/sort-by-date")
    public ResponseEntity<Response> sortByDate() {
        try {
            return ResponseEntity.ok(new Response("Successfully get Feedbacks.", feedbackService.sortByFeedbackDate()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Feedbacks. " + exception.getMessage(), null));
        }
    }
}
