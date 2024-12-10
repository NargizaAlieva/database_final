package org.example.database_final.repository;

import org.example.database_final.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for `Enrollment` entities.
 * This interface extends JpaRepository, providing basic CRUD methods.
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /**
     * Checks if an enrollment ID exists in the database.
     *
     * @param id The ID of the enrollment to check.
     * @return `true` if the ID exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Enrollment e WHERE e.id = ?1")
    boolean isIdExist(Long id);

    /**
     * Retrieves an enrollment by its ID.
     *
     * @param id The ID of the enrollment.
     * @return An `Optional` containing the found enrollment, or empty if not found.
     */
    @Query("SELECT e FROM Enrollment e WHERE e.id = ?1")
    Optional<Enrollment> findById(Long id);

    /**
     * Retrieves a list of enrollments for a specific student by their ID.
     *
     * @param studentId The ID of the student.
     * @return A list of `Enrollment` objects associated with the student.
     */
    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentStudent.id = ?1")
    List<Enrollment> findByStudentId(Long studentId);

    /**
     * Retrieves a list of enrollments for a specific course by its ID.
     *
     * @param courseId The ID of the course.
     * @return A list of `Enrollment` objects associated with the course.
     */
    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentCourse.id = ?1")
    List<Enrollment> findByCourseId(Long courseId);

    /**
     * Retrieves an enrollment by a student's name and course ID.
     *
     * @param studentName The name of the student.
     * @param courseId The ID of the course.
     * @return An `Enrollment` object if a match is found, otherwise `null`.
     */
    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentCourse.id = ?2 " +
            "AND e.enrollmentStudent.id = (SELECT s.id FROM Student s WHERE s.name = ?1)")
    Enrollment findByStudentNameAndCourseId(String studentName, Long courseId);
}
