package org.example.database_final.service;

import org.example.database_final.dto.FeedbackDto;
import org.example.database_final.entity.Feedback;

import java.util.List;

/**
 * Service interface for managing `Feedback` entities.
 * This service provides methods for handling feedback data including CRUD operations.
 */
public interface FeedbackService {

    /**
     * Retrieves a `Feedback` by its unique ID.
     *
     * @param id The unique identifier of the feedback.
     * @return The `Feedback` entity corresponding to the provided ID.
     */
    Feedback getFeedbackById(Long id);

    /**
     * Retrieves a list of `FeedbackDto` objects by course name.
     *
     * @param courseName The name of the course for which feedback is to be retrieved.
     * @return A list of `FeedbackDto` objects for the specified course.
     */
    List<FeedbackDto> getFeedbackByCourseName(String courseName);

    /**
     * Retrieves a list of `FeedbackDto` objects by student name.
     *
     * @param studentName The name of the student for which feedback is to be retrieved.
     * @return A list of `FeedbackDto` objects for the specified student.
     */
    List<FeedbackDto> getFeedbackByStudentName(String studentName);

    /**
     * Retrieves all feedbacks available in the system.
     *
     * @return A list of `FeedbackDto` objects representing all feedbacks.
     */
    List<FeedbackDto> getAllFeedbacks();

    /**
     * Creates a new feedback.
     *
     * @param feedbackDtoRequest The data transfer object containing the details of the feedback to be created.
     * @return The created `FeedbackDto` object.
     */
    FeedbackDto createFeedback(FeedbackDto feedbackDtoRequest);

    /**
     * Updates an existing feedback.
     *
     * @param feedbackDtoRequest The data transfer object containing the updated details of the feedback.
     * @return The updated `FeedbackDto` object.
     */
    FeedbackDto updateFeedback(FeedbackDto feedbackDtoRequest);

    /**
     * Deletes a feedback by its ID.
     *
     * @param id The unique identifier of the feedback to be deleted.
     */
    void deleteFeedback(Long id);

    /**
     * Sorts feedbacks by rating.
     *
     * @return A list of `FeedbackDto` objects sorted by rating.
     */
    List<FeedbackDto> sortByRating();

    /**
     * Sorts feedbacks by feedback date.
     *
     * @return A list of `FeedbackDto` objects sorted by feedback date.
     */
    List<FeedbackDto> sortByFeedbackDate();
}
