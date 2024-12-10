package org.example.database_final.controller;

import org.example.dp_project.dto.InstructorDto;
import org.example.dp_project.dto.Response;
import org.example.dp_project.service.InstructorService;
import org.example.dp_project.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping(value = "/get-instructor-by-name/{instructorName}")
    public ResponseEntity<Response> getInstructorByName(@PathVariable String instructorName) {
        try {
            return ResponseEntity.ok(new Response("Successfully get Instructor.", instructorService.getInstructorByName(instructorName)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to get Instructor. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-instructor")
    public ResponseEntity<Response> getAllInstructor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Instructor.", instructorService.getAllInstructors()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-instructor")
    public ResponseEntity<Response> createInstructor(@RequestBody InstructorDto request) {
        try {
            InstructorDto instructorDto = instructorService.createInstructor(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Instructor.", instructorDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Instructor is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-instructor")
    public ResponseEntity<Response> updateInstructor(@RequestBody InstructorDto request) {
        try {
            InstructorDto instructorDto = instructorService.updateInstructor(request);
            return ResponseEntity.ok(new Response("Updated Instructor successfully.", instructorDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Instructor is not updated. " + exception.getMessage(), null));
        }
    }
}
