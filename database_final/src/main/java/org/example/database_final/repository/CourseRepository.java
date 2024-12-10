package org.example.database_final.repository;

import org.example.database_final.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for `Course` entities.
 * This interface extends JpaRepository, providing basic CRUD methods.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Checks if a course ID exists in the database.
     *
     * @param id The ID of the course to check.
     * @return `true` if the ID exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Course c WHERE c.id = ?1")
    boolean isIdExist(Long id);

    /**
     * Retrieves a course by its ID.
     *
     * @param id The ID of the course.
     * @return An `Optional` containing the found course, or empty if not found.
     */
    @Query("SELECT c FROM Course c WHERE c.id = ?1")
    Optional<Course> findById(Long id);

    /**
     * Finds courses whose titles contain the specified string.
     *
     * @param title The title to search for within course titles.
     * @return A list of courses with titles containing the specified string.
     */
    @Query("SELECT c FROM Course c WHERE LOWER(c.title) LIKE %LOWER(?1)%")
    List<Course> findByNameContains(String title);

    /**
     * Finds a course by its exact title.
     *
     * @param title The exact title of the course.
     * @return An `Optional` containing the found course, or empty if not found.
     */
    @Query("SELECT c FROM Course c WHERE LOWER(c.title) = LOWER(?1)")
    Optional<Course> findByName(String title);

    /**
     * Finds courses by their category name.
     * This uses a subquery to first find the ID of the category by name.
     *
     * @param categoryName The name of the category.
     * @return A list of courses in the specified category.
     */
    @Query("SELECT c FROM Course c WHERE c.courseCategory.id = (SELECT id FROM Category WHERE LOWER(name) = LOWER(?1))")
    List<Course> findCoursesByCategoryName(String categoryName);

    /**
     * Finds courses by the instructor's name.
     * This uses a subquery to find the ID of the instructor by name.
     *
     * @param instructorName The name of the instructor.
     * @return A list of courses taught by the specified instructor.
     */
    @Query("SELECT c FROM Course c WHERE c.courseInstructor.id = (SELECT id FROM Instructor WHERE LOWER(name) = LOWER(?1))")
    List<Course> findCoursesByInstructorName(String instructorName);

    /**
     * Finds courses by the student's name.
     * This joins `Course`, `Enrollment`, and `Student` tables to find courses in which the student is enrolled.
     *
     * @param studentName The name of the student.
     * @return A list of courses in which the specified student is enrolled.
     */
    @Query("SELECT c FROM Course c JOIN Enrollment e ON c.id = e.enrollmentCourse.id " +
            "JOIN Student s ON e.enrollmentStudent.id = s.id WHERE LOWER(s.name) = LOWER(?1)")
    List<Course> findCourseByStudentName(String studentName);
}
