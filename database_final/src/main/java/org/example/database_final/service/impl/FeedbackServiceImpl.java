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

/**
 * Service implementation for managing feedback.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    /**
     * Constructor for FeedbackServiceImpl.
     *
     * @param feedbackRepository the repository for accessing feedback data.
     * @param feedbackMapper     the mapper for converting between Feedback entities and DTOs.
     */
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    /**
     * Checks if a feedback ID exists in the repository.
     *
     * @param id the feedback ID.
     * @return false (no return value used).
     * @throws AlreadyExistException if the feedback ID already exists.
     */
    private boolean isIdExist(Long id) {
        if (feedbackRepository.existsById(id))
            throw new AlreadyExistException("Feedback", "id");
        return false;
    }

    /**
     * Saves a feedback entity to the database.
     *
     * @param feedback the feedback entity to save.
     * @return the saved feedback entity.
     */
    private Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    /**
     * Retrieves a feedback by its ID.
     *
     * @param id the ID of the feedback.
     * @return the feedback entity.
     * @throws ObjectNotFoundException if the feedback with the given ID does not exist.
     */
    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Feedback"));
    }

    /**
     * Retrieves feedback by course name.
     *
     * @param courseName the course name.
     * @return a list of feedback DTOs.
     */
    @Override
    public List<FeedbackDto> getFeedbackByCourseName(String courseName) {
        return feedbackMapper.entityToDtoList(feedbackRepository.findByCourseName(courseName));
    }

    /**
     * Retrieves feedback by student name.
     *
     * @param studentName the student name.
     * @return a list of feedback DTOs.
     */
    @Override
    public List<FeedbackDto> getFeedbackByStudentName(String studentName) {
        return feedbackMapper.entityToDtoList(feedbackRepository.findByStudentName(studentName));
    }

    /**
     * Retrieves all feedbacks.
     *
     * @return a list of feedback DTOs.
     */
    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackMapper.entityToDtoList(feedbackRepository.findAll());
    }

    /**
     * Creates a new feedback.
     *
     * @param feedbackDtoRequest the feedback DTO containing the new feedback's details.
     * @return the created feedback DTO.
     * @throws AlreadyExistException if a feedback with the same details already exists.
     */
    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDtoRequest) {
        return feedbackMapper.entityToDto(save(feedbackMapper.dtoToEntity(feedbackDtoRequest)));
    }

    /**
     * Updates an existing feedback.
     *
     * @param feedbackDtoRequest the feedback DTO containing the updated details.
     * @return the updated feedback DTO.
     */
    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackDtoRequest) {
        Feedback oldFeedback = feedbackMapper.dtoToEntity(feedbackDtoRequest);
        Feedback newFeedback = getFeedbackById(feedbackDtoRequest.getId());

        // Update only non-null fields from the request DTO to the existing feedback entity
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

    /**
     * Deletes feedback by ID.
     *
     * @param id the feedback ID.
     */
    @Override
    public void deleteFeedback(Long id) {
        Feedback feedback = getFeedbackById(id);
        feedbackRepository.delete(feedback);
    }

    /**
     * Sorts feedback by rating.
     *
     * @return a sorted list of feedback DTOs by rating.
     */
    @Override
    public List<FeedbackDto> sortByRating() {
        return getAllFeedbacks().stream()
                .sorted(Comparator.comparing(FeedbackDto::getRating))
                .collect(Collectors.toList());
    }

    /**
     * Sorts feedback by feedback date.
     *
     * @return a sorted list of feedback DTOs by feedback date.
     */
    @Override
    public List<FeedbackDto> sortByFeedbackDate() {
        return getAllFeedbacks().stream()
                .sorted(Comparator.comparing(FeedbackDto::getFeedbackDate))
                .collect(Collectors.toList());
    }
}
