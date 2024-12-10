package org.example.database_final.repository;

import org.example.database_final.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.id = ?1")
    boolean isIdExist(Long id);
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.name = ?1")
    boolean isEmailExist(String name);
    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findById(Long id);
    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Optional<Student> findByName(String name);
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Student> findByNameContains(String name);
    @Query("SELECT s FROM Student s JOIN Enrollment e ON s.id = e.enrollmentStudent.id WHERE e.enrollmentCourse.id = ?1")
    List<Student> findStudentsByCourseId(Long courseId);
}
