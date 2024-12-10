package org.example.database_final.service;

import org.example.database_final.dto.StudentDto;
import org.example.database_final.entity.Student;

import java.util.List;

/**
 * Service interface for managing `Student` entities.
 * This service provides methods for handling student data including CRUD operations.
 */
public interface StudentService {

    /**
     * Retrieves a `Student` by its unique ID.
     *
     * @param id The unique identifier of the student.
     * @return The `Student` entity corresponding to the provided ID.
     */
    Student getStudentById(Long id);

    /**
     * Retrieves a `Student` entity by its name.
     *
     * @param name The name of the student.
     * @return The `Student` entity corresponding to the provided name.
     */
    Student getStudentEntityByName(String name);

    /**
     * Retrieves a list of `StudentDto` objects by student name.
     *
     * @param name The name of the student for which information is to be retrieved.
     * @return A list of `StudentDto` objects for the specified student.
     */
    List<StudentDto> getStudentByName(String name);

    /**
     * Retrieves a list of `StudentDto` objects by course ID.
     *
     * @param courseId The unique identifier of the course.
     * @return A list of `StudentDto` objects enrolled in the specified course.
     */
    List<StudentDto> getStudentsByCourseId(Long courseId);

    /**
     * Retrieves all students available in the system.
     *
     * @return A list of `StudentDto` objects representing all students.
     */
    List<StudentDto> getAllStudents();

    /**
     * Creates a new student.
     *
     * @param studentDto The data transfer object containing the details of the student to be created.
     * @return The created `StudentDto` object.
     */
    StudentDto createStudent(StudentDto studentDto);

    /**
     * Updates an existing student.
     *
     * @param studentDto The data transfer object containing the updated details of the student.
     * @return The updated `StudentDto` object.
     */
    StudentDto updateStudent(StudentDto studentDto);

    /**
     * Sorts students by name.
     *
     * @return A list of `StudentDto` objects sorted by name.
     */
    List<StudentDto> sortByName();
}
