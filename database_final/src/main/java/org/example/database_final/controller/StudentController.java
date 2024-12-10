package org.example.database_final.controller;

import org.example.database_final.dto.Response;
import org.example.database_final.dto.StudentDto;
import org.example.database_final.service.StudentService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing students.
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentService studentService;

    /**
     * Constructor for StudentController.
     *
     * @param studentService the service for handling student operations.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Retrieves a student by their name.
     *
     * @param studentName the name of the student to retrieve.
     * @return a ResponseEntity containing a Response object with the student data or an error message.
     */
    @GetMapping(value = "/get-student-by-name/{studentName}")
    public ResponseEntity<Response> getStudentByName(@PathVariable String studentName) {
        try {
            List<StudentDto> students = studentService.getStudentByName(studentName);
            return ResponseEntity.ok(new Response("Successfully retrieved Student.", students));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Student. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves students by course ID.
     *
     * @param courseId the ID of the course to retrieve students for.
     * @return a ResponseEntity containing a Response object with the list of students or an error message.
     */
    @GetMapping(value = "/get-student-by-course-id/{courseId}")
    public ResponseEntity<Response> getStudentByCourseId(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved Students.", studentService.getStudentsByCourseId(courseId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Student. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves all students.
     *
     * @return a ResponseEntity containing a Response object with a list of all students or an error message.
     */
    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved all Students.", studentService.getAllStudents()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find any Students. " + exception.getMessage(), null));
        }
    }

    /**
     * Creates a new student.
     *
     * @param request the DTO containing the new student data.
     * @return a ResponseEntity containing a Response object with the created student data or an error message.
     */
    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDto request) {
        try {
            StudentDto studentDto = studentService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Student.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student could not be saved. " + exception.getMessage(), null));
        }
    }

    /**
     * Updates an existing student.
     *
     * @param request the DTO containing the updated student data.
     * @return a ResponseEntity containing a Response object with the updated student data or an error message.
     */
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDto request) {
        try {
            StudentDto studentDto = studentService.updateStudent(request);
            return ResponseEntity.ok(new Response("Updated Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student could not be updated. " + exception.getMessage(), null));
        }
    }

    /**
     * Sorts students by name.
     *
     * @return a ResponseEntity containing a Response object with the sorted list of students or an error message.
     */
    @GetMapping(value = "/sort-by-name")
    public ResponseEntity<Response> sortByName() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved sorted Students.", studentService.sortByName()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve sorted Students. " + exception.getMessage(), null));
        }
    }
}
