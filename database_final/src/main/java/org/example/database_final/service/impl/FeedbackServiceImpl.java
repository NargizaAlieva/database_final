package org.example.database_final.service.impl;

import org.example.database_final.dto.*;
import org.example.database_final.entity.Feedback;
import org.example.database_final.repository.FeedbackRepository;
import org.example.database_final.service.FeedbackService;
import org.example.database_final.utils.exception.AlreadyExistException;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.example.database_final.utils.mapper.FeedbackMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    private boolean isIdExist(Long id) {
        if (feedbackRepository.existsById(id))
            throw new AlreadyExistException("Feedback", "id");
        return false;
    }

    private Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
         return feedbackRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Feedback"));
    }

    @Override
    public List<FeedbackDto> getFeedbackByCourseName(String courseName) {
        return feedbackMapper.entityToDtoList(feedbackRepository.findByCourseName(courseName));
    }

    @Override
    public List<FeedbackDto> getFeedbackByStudentName(String studentName) {
        return feedbackMapper.entityToDtoList(feedbackRepository.findByStudentName(studentName));
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackMapper.entityToDtoList(feedbackRepository.findAll());
    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDtoRequest) {
        return feedbackMapper.entityToDto(save(feedbackMapper.dtoToEntity(feedbackDtoRequest)));
    }

    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackDtoRequest) {
        Feedback oldFeedback = feedbackMapper.dtoToEntity(feedbackDtoRequest);
        Feedback newFeedback = getFeedbackById(feedbackDtoRequest.getId());

        if (!(oldFeedback.getComment() == null))
            newFeedback.setComment(oldFeedback.getComment());
        if (!(oldFeedback.getRating() == null))
            newFeedback.setRating(oldFeedback.getRating());
        if (!(oldFeedback.getFeedbackDate() == null))
            newFeedback.setFeedbackDate(oldFeedback.getFeedbackDate());
        if (!(oldFeedback.getFeedbackCourse() == null))
            newFeedback.setFeedbackCourse(oldFeedback.getFeedbackCourse());
        if (!(oldFeedback.getFeedbackStudent() == null))
            newFeedback.setFeedbackStudent(oldFeedback.getFeedbackStudent());

        return feedbackMapper.entityToDto(save(newFeedback));
    }

    @Override
    public void deleteFeedback(Long id) {
        Feedback feedback = getFeedbackById(id);
        feedbackRepository.delete(feedback);
    }

    @Override
    public List<FeedbackDto> sortByRating() {
        return getAllFeedbacks().stream()
                .sorted(Comparator.comparing(FeedbackDto::getRating))
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDto> sortByFeedbackDate() {
        return getAllFeedbacks().stream()
                .sorted(Comparator.comparing(FeedbackDto::getFeedbackDate))
                .collect(Collectors.toList());
    }
}
