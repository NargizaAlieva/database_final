package org.example.database_final.service;

import org.example.database_final.dto.FeedbackDto;
import org.example.database_final.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback getFeedbackById(Long id);
    List<FeedbackDto> getFeedbackByCourseName(String courseName);
    List<FeedbackDto> getFeedbackByStudentName(String studentName);
    List<FeedbackDto> getAllFeedbacks();

    FeedbackDto createFeedback(FeedbackDto feedbackDtoRequest);
    FeedbackDto updateFeedback(FeedbackDto feedbackDtoRequest);
    void deleteFeedback(Long id);

    List<FeedbackDto> sortByRating();
    List<FeedbackDto> sortByFeedbackDate();
}
