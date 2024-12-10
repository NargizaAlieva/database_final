package org.example.database_final.utils.mapper;

import org.example.dp_project.dto.CourseDtoRequest;
import org.example.dp_project.dto.CourseDtoResponse;
import org.example.dp_project.entity.Category;
import org.example.dp_project.entity.Course;
import org.example.dp_project.entity.Instructor;
import org.example.dp_project.service.CategoryService;
import org.example.dp_project.service.InstructorService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {
    private final InstructorService instructorService;
    private final CategoryService categoryService;

    public CourseMapper(InstructorService instructorService, CategoryService categoryService) {
        this.instructorService = instructorService;
        this.categoryService = categoryService;
    }

    public CourseDtoResponse entityToDto(Course course) {
        CourseDtoResponse courseDtoResponse = new CourseDtoResponse();

        Instructor instructor = instructorService.getInstructorById(course.getInstructor().getId());
        Category category = categoryService.getCategoryById(course.getCategory().getId());

        courseDtoResponse.setId(course.getId());
        courseDtoResponse.setTitle(course.getTitle());
        courseDtoResponse.setDescription(course.getDescription());
        courseDtoResponse.setDuration(course.getDuration());
        courseDtoResponse.setPrice(course.getPrice());
        courseDtoResponse.setInstructorName(instructor.getName());
        courseDtoResponse.setCategoryName(category.getName());
        courseDtoResponse.setTotalEnrollments(course.getTotalEnrollments());
        courseDtoResponse.setAverageRating((float) (Math.round(course.getAverageRating() * 10) / 10.0));
        courseDtoResponse.setCreationDate(course.getCreationDate());
        return courseDtoResponse;
    }

    public List<CourseDtoResponse> entityToDtoList(List<Course> courses) {
        return courses.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Course dtoToEntity (CourseDtoRequest courseDtoRequest) {
        Course course = new Course();
        course.setId(courseDtoRequest.getId());
        course.setTitle(courseDtoRequest.getTitle());
        course.setDescription(courseDtoRequest.getDescription());
        course.setDuration(courseDtoRequest.getDuration());
        course.setPrice(courseDtoRequest.getPrice());
        course.setInstructor(instructorService.getInstructorEntityByName(courseDtoRequest.getInstructorName()));
        course.setCategory(categoryService.getCategoryEntityByName(courseDtoRequest.getCategoryName()));
        course.setCreationDate(course.getCreationDate());
        return course;
    }
}
