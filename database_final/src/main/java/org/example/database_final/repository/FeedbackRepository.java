package org.example.database_final.repository;

import org.example.database_final.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for `Feedback` entities.
 * This interface extends JpaRepository, providing basic CRUD methods.
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    /**
     * Checks if a feedback ID exists in the database.
     *
     * @param id The ID of the feedback to check.
     * @return `true` if the ID exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Feedback f WHERE f.id = ?1")
    boolean isIdExist(Long id);

    /**
     * Retrieves a feedback by its ID.
     *
     * @param id The ID of the feedback.
     * @return An `Optional` containing the found feedback, or empty if not found.
     */
    @Query("SELECT f FROM Feedback f WHERE f.id = ?1")
    Optional<Feedback> findById(Long id);

    /**
     * Retrieves a list of feedbacks given by a student based on the student's name.
     *
     * @param studentName The name of the student.
     * @return A list of `Feedback` objects associated with the student.
     */
    @Query("SELECT f FROM Feedback f JOIN Student s ON f.feedbackStudent.id = s.id WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Feedback> findByStudentName(String studentName);

    /**
     * Retrieves a list of feedbacks for a specific course based on the course title.
     *
     * @param courseName The title of the course.
     * @return A list of `Feedback` objects associated with the course.
     */
    @Query("SELECT f FROM Feedback f JOIN Course c ON f.feedbackCourse.id = c.id WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Feedback> findByCourseName(String courseName);
}
