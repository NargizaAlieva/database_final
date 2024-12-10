package org.example.database_final.repository;

import org.example.database_final.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Feedback f WHERE f.id = ?1")
    boolean isIdExist(Long id);
    @Query("SELECT f FROM Feedback f WHERE f.id = ?1")
    Optional<Feedback> findById(Long id);
    @Query("SELECT f FROM Feedback f WHERE f.feedbackStudent.id = ?1")
    List<Feedback> findByStudentId(Long studentId);
    @Query("SELECT f FROM Feedback f WHERE f.feedbackCourse.id = ?1")
    List<Feedback> findByCourseId(Long courseId);
}
