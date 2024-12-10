package org.example.database_final.repository;

import org.example.database_final.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Enrollment e WHERE e.id = ?1")
    boolean isIdExist(Long id);
    @Query("SELECT e FROM Enrollment e WHERE e.id = ?1")
    Optional<Enrollment> findById(Long id);
    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentStudent.id = ?1")
    List<Enrollment> findByStudentId(Long studentId);
    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentCourse.id = ?1")
    List<Enrollment> findByCourseId(Long courseId);
    @Query("SELECT e FROM Enrollment e WHERE e.enrollmentCourse.id = ?2 " +
            "AND e.enrollmentStudent.id = (SELECT s.id FROM Student s WHERE s.name = ?1)")
    Enrollment findByStudentNameAndCourseId(String studentName, Long courseId);
}
