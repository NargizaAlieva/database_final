package org.example.database_final.service;

import org.example.database_final.dto.FeedbackDto;
import org.example.database_final.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback getFeedbackById(Long id);
    List<FeedbackDto> getFeedbackByCourseId(Long courseId);
    List<FeedbackDto> getFeedbackByStudentId(Long studentId);
    List<FeedbackDto> getAllFeedbacks();

    FeedbackDto createFeedback(FeedbackDto feedbackDtoRequest);
    FeedbackDto updateFeedback(FeedbackDto feedbackDtoRequest);

    List<FeedbackDto> sortByRating(List<FeedbackDto> feedbackDtoList);
    List<FeedbackDto> sortByFeedbackDate(List<FeedbackDto> feedbackDtoList);
}
