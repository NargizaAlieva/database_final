package org.example.database_final.repository;

import org.example.database_final.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for `Student` entities.
 * This interface extends JpaRepository, providing basic CRUD methods.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Checks if a student ID exists in the database.
     *
     * @param id The ID of the student to check.
     * @return `true` if the ID exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.id = ?1")
    boolean isIdExist(Long id);

    /**
     * Checks if a student name exists in the database.
     *
     * @param name The name of the student to check.
     * @return `true` if the name exists, otherwise `false`.
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.name = ?1")
    boolean isEmailExist(String name);

    /**
     * Retrieves a student by their ID.
     *
     * @param id The ID of the student.
     * @return An `Optional` containing the found student, or empty if not found.
     */
    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findById(Long id);

    /**
     * Retrieves a student by their name.
     *
     * @param name The name of the student.
     * @return An `Optional` containing the found student, or empty if not found.
     */
    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Optional<Student> findByName(String name);

    /**
     * Retrieves a list of students whose names contain the specified substring.
     *
     * @param name The substring to search for in student names.
     * @return A list of `Student` objects whose names contain the given substring.
     */
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Student> findByNameContains(String name);

    /**
     * Retrieves a list of students enrolled in a specific course based on the course ID.
     *
     * @param courseId The ID of the course.
     * @return A list of `Student` objects enrolled in the specified course.
     */
    @Query("SELECT s FROM Student s JOIN Enrollment e ON s.id = e.enrollmentStudent.id WHERE e.enrollmentCourse.id = ?1")
    List<Student> findStudentsByCourseId(Long courseId);
}
