package org.example.database_final.utils.mapper;

import org.example.database_final.dto.FeedbackDto;
import org.example.database_final.entity.Feedback;
import org.example.database_final.service.CourseService;
import org.example.database_final.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between `Feedback` entities and `FeedbackDto` data transfer objects (DTOs).
 * This class provides methods to transform feedback data from the entity layer to the DTO layer and vice versa.
 * It uses services to fetch related entities for Course and Student for detailed mappings.
 */
@Component
public class FeedbackMapper {
    private final CourseService courseService;
    private final StudentService studentService;

    /**
     * Constructor for `FeedbackMapper`.
     *
     * @param courseService  The service to fetch course details.
     * @param studentService The service to fetch student details.
     */
    public FeedbackMapper(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    /**
     * Converts a `Feedback` entity to a `FeedbackDto`.
     *
     * @param feedback The `Feedback` entity to be converted.
     * @return The corresponding `FeedbackDto`.
     */
    public FeedbackDto entityToDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setComment(feedback.getComment());
        feedbackDto.setFeedbackDate(feedback.getFeedbackDate());
        feedbackDto.setRating(feedback.getRating());
        feedbackDto.setCourseId(feedback.getFeedbackCourse().getId());
        feedbackDto.setCourseName(courseService.getCourseById(feedback.getFeedbackCourse().getId()).getTitle());
        feedbackDto.setStudentName(feedback.getFeedbackStudent().getName());
        return feedbackDto;
    }

    /**
     * Converts a list of `Feedback` entities to a list of `FeedbackDto` objects.
     *
     * @param feedbacks The list of `Feedback` entities to be converted.
     * @return A list of `FeedbackDto` objects.
     */
    public List<FeedbackDto> entityToDtoList(List<Feedback> feedbacks) {
        return feedbacks.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    /**
     * Converts a `FeedbackDto` to a `Feedback` entity.
     *
     * @param feedbackDto The `FeedbackDto` to be converted.
     * @return The corresponding `Feedback` entity.
     */
    public Feedback dtoToEntity (FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDto.getId());
        feedback.setComment(feedbackDto.getComment());
        feedback.setFeedbackDate(feedbackDto.getFeedbackDate());
        feedback.setRating(feedbackDto.getRating());
        feedback.setFeedbackCourse(courseService.getCourseById(feedbackDto.getCourseId()));
        feedback.setFeedbackStudent(studentService.getStudentEntityByName(feedbackDto.getStudentName()));
        return feedback;
    }
}
