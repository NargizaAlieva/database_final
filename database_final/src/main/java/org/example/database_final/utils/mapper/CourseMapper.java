package org.example.database_final.utils.mapper;

import org.example.database_final.dto.CourseDtoRequest;
import org.example.database_final.dto.CourseDtoResponse;
import org.example.database_final.entity.Category;
import org.example.database_final.entity.Course;
import org.example.database_final.entity.Instructor;
import org.example.database_final.service.CategoryService;
import org.example.database_final.service.InstructorService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between `Course` entities and `CourseDtoRequest` and `CourseDtoResponse` data transfer objects (DTOs).
 * This class provides methods to transform course data from the entity layer to the DTO layer and vice versa.
 * It uses services to fetch related entities for Instructor and Category for detailed mappings.
 */
@Component
public class CourseMapper {
    private final InstructorService instructorService;
    private final CategoryService categoryService;

    /**
     * Constructor for `CourseMapper`.
     *
     * @param instructorService The service to fetch instructor details.
     * @param categoryService   The service to fetch category details.
     */
    public CourseMapper(InstructorService instructorService, CategoryService categoryService) {
        this.instructorService = instructorService;
        this.categoryService = categoryService;
    }

    /**
     * Converts a `Course` entity to a `CourseDtoResponse`.
     *
     * @param course The `Course` entity to be converted.
     * @return The corresponding `CourseDtoResponse`.
     */
    public CourseDtoResponse entityToDto(Course course) {
        if (course == null) {
            return null;
        }
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

    /**
     * Converts a list of `Course` entities to a list of `CourseDtoResponse` objects.
     *
     * @param courses The list of `Course` entities to be converted.
     * @return A list of `CourseDtoResponse` objects.
     */
    public List<CourseDtoResponse> entityToDtoList(List<Course> courses) {
        if (courses == null) {
            return null;
        }
        return courses.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    /**
     * Converts a `CourseDtoRequest` to a `Course` entity.
     *
     * @param courseDtoRequest The `CourseDtoRequest` to be converted.
     * @return The corresponding `Course` entity.
     */
    public Course dtoToEntity(CourseDtoRequest courseDtoRequest) {
        if (courseDtoRequest == null) {
            return null;
        }
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
