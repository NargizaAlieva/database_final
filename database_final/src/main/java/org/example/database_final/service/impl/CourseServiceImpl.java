package org.example.database_final.service.impl;

import org.example.database_final.dto.CourseDtoRequest;
import org.example.database_final.dto.CourseDtoResponse;
import org.example.database_final.entity.Course;
import org.example.database_final.repository.CourseRepository;
import org.example.database_final.service.CourseService;
import org.example.database_final.utils.exception.AlreadyExistException;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.example.database_final.utils.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing courses.
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    /**
     * Constructor for CourseServiceImpl.
     *
     * @param courseRepository the repository for accessing course data.
     * @param courseMapper     the mapper for converting between Course entities and DTOs.
     */
    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    /**
     * Checks if a course with the given ID exists.
     *
     * @param id the course ID.
     * @return false (no return value used).
     * @throws AlreadyExistException if the course ID already exists.
     */
    private boolean isIdExist(Long id) {
        if (courseRepository.existsById(id)) {
            throw new AlreadyExistException("Course", "id");
        }
        return false;
    }

    /**
     * Saves a course entity to the database.
     *
     * @param course the course entity to save.
     * @return the saved course entity.
     */
    private Course save(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Retrieves a course by ID.
     *
     * @param id the ID of the course.
     * @return the course entity.
     * @throws ObjectNotFoundException if the course with the given ID does not exist.
     */
    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Course"));
    }

    /**
     * Retrieves a course entity by name.
     *
     * @param name the name of the course.
     * @return the course entity.
     * @throws ObjectNotFoundException if the course with the given name does not exist.
     */
    @Override
    public Course getCourseEntityByName(String name) {
        return courseRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Course"));
    }

    /**
     * Retrieves a list of course DTO responses by name.
     *
     * @param name the name of the course.
     * @return a list of course DTO responses.
     * @throws ObjectNotFoundException if no courses with the given name exist.
     */
    @Override
    public List<CourseDtoResponse> getCourseByName(String name) {
        return courseMapper.entityToDtoList(courseRepository.findByNameContains(name));
    }

    /**
     * Retrieves all courses as DTO responses.
     *
     * @return a list of all course DTO responses.
     */
    @Override
    public List<CourseDtoResponse> getAllCourses() {
        return courseMapper.entityToDtoList(courseRepository.findAll());
    }

    /**
     * Retrieves courses by category name as DTO responses.
     *
     * @param categoryName the category name.
     * @return a list of course DTO responses.
     */
    @Override
    public List<CourseDtoResponse> getCoursesByCategoryName(String categoryName) {
        return courseMapper.entityToDtoList(courseRepository.findCoursesByCategoryName(categoryName));
    }

    /**
     * Retrieves courses by instructor name as DTO responses.
     *
     * @param instructorName the instructor name.
     * @return a list of course DTO responses.
     */
    @Override
    public List<CourseDtoResponse> getCoursesByInstructorName(String instructorName) {
        return courseMapper.entityToDtoList(courseRepository.findCoursesByInstructorName(instructorName));
    }

    /**
     * Retrieves courses by student name as DTO responses.
     *
     * @param studentName the student name.
     * @return a list of course DTO responses.
     */
    @Override
    public List<CourseDtoResponse> getCoursesByStudentName(String studentName) {
        return courseMapper.entityToDtoList(courseRepository.findCourseByStudentName(studentName));
    }

    /**
     * Creates a new course.
     *
     * @param courseDtoRequest the course DTO request containing the new course details.
     * @return the created course DTO response.
     */
    @Override
    public CourseDtoResponse createCourse(CourseDtoRequest courseDtoRequest) {
        Course course = courseMapper.dtoToEntity(courseDtoRequest);
        course.setAverageRating(0.0F);
        course.setTotalEnrollments(0);
        return courseMapper.entityToDto(save(course));
    }

    /**
     * Updates an existing course.
     *
     * @param courseDtoRequest the course DTO request containing the updated details.
     * @return the updated course DTO response.
     * @throws ObjectNotFoundException if the course with the given ID does not exist.
     */
    @Override
    public CourseDtoResponse updateCourse(CourseDtoRequest courseDtoRequest) {
        Course oldCourse = courseMapper.dtoToEntity(courseDtoRequest);
        Course newCourse = getCourseById(courseDtoRequest.getId());

        // Update course details if new values are provided.
        if (oldCourse.getTitle() != null) {
            newCourse.setTitle(oldCourse.getTitle());
        }
        if (oldCourse.getDescription() != null) {
            newCourse.setDescription(oldCourse.getDescription());
        }
        if (oldCourse.getDuration() != null) {
            newCourse.setDuration(oldCourse.getDuration());
        }
        if (oldCourse.getPrice() != null) {
            newCourse.setPrice(oldCourse.getPrice());
        }
        if (oldCourse.getInstructor() != null) {
            newCourse.setInstructor(oldCourse.getInstructor());
        }
        if (oldCourse.getCategory() != null) {
            newCourse.setCategory(oldCourse.getCategory());
        }

        return courseMapper.entityToDto(save(newCourse));
    }

    /**
     * Sorts courses by duration.
     *
     * @return a list of course DTO responses sorted by duration.
     */
    @Override
    public List<CourseDtoResponse> sortByDuration() {
        return getAllCourses().stream()
                .sorted(Comparator.comparingInt(CourseDtoResponse::getDuration))
                .collect(Collectors.toList());
    }

    /**
     * Sorts courses by price.
     *
     * @return a list of course DTO responses sorted by price.
     */
    @Override
    public List<CourseDtoResponse> sortByPrice() {
        return getAllCourses().stream()
                .sorted(Comparator.comparingDouble(CourseDtoResponse::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Sorts courses by total enrollments.
     *
     * @return a list of course DTO responses sorted by total enrollments.
     */
    @Override
    public List<CourseDtoResponse> sortByEnrollments() {
        return getAllCourses().stream()
                .sorted(Comparator.comparingInt(CourseDtoResponse::getTotalEnrollments))
                .collect(Collectors.toList());
    }

    /**
     * Sorts courses by average rating.
     *
     * @return a list of course DTO responses sorted by average rating in descending order.
     */
    @Override
    public List<CourseDtoResponse> sortByRating() {
        return getAllCourses().stream()
                .sorted(Comparator.comparingDouble(CourseDtoResponse::getAverageRating).reversed())
                .collect(Collectors.toList());
    }
}
