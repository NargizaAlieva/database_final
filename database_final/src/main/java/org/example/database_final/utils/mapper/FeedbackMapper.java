package org.example.database_final.utils.mapper;

import org.example.database_final.dto.FeedbackDto;
import org.example.database_final.entity.Feedback;
import org.example.database_final.service.CourseService;
import org.example.database_final.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {
    private CourseService courseService;
    private StudentService studentService;

    public FeedbackMapper(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    public FeedbackDto entityToDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setComment(feedback.getComment());
        feedbackDto.setFeedbackDate(feedback.getFeedbackDate());
        feedbackDto.setRating(feedback.getRating());
        feedbackDto.setCourseName(feedback.getFeedbackCourse().getTitle());
        feedbackDto.setStudentName(feedback.getFeedbackStudent().getName());
        return feedbackDto;
    }

    public List<FeedbackDto> entityToDtoList(List<Feedback> feedbacks) {
        return feedbacks.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Feedback dtoToEntity (FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDto.getId());
        feedback.setComment(feedbackDto.getComment());
        feedback.setFeedbackDate(feedbackDto.getFeedbackDate());
        feedback.setRating(feedbackDto.getRating());
        feedback.setFeedbackCourse(courseService.getCourseEntityByName(feedbackDto.getCourseName()));
        feedback.setFeedbackStudent(studentService.getStudentEntityByName(feedbackDto.getStudentName()));
        return feedback;
    }
}
