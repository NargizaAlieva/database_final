package org.example.database_final.controller;

import org.example.database_final.dto.Response;
import org.example.database_final.dto.StudentDto;
import org.example.database_final.service.StudentService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/get-student-by-name/{studentName}")
    public ResponseEntity<Response> getStudentByName(@PathVariable String studentName) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Students.", studentService.getStudentByName(studentName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Student. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-student-by-course-id/{courseId}")
    public ResponseEntity<Response> getStudentByCourseId(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Students.", studentService.getStudentsByCourseId(courseId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Student. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Students.", studentService.getAllStudents()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDto request) {
        try {
            StudentDto studentDto = studentService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Student.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDto request) {
        try {
            StudentDto studentDto = studentService.updateStudent(request);
            return ResponseEntity.ok(new Response("Updated Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not updated. " + exception.getMessage(), null));
        }
    }
}
