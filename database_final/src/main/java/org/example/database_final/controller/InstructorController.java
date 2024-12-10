package org.example.database_final.controller;

import org.example.database_final.dto.InstructorDto;
import org.example.database_final.dto.Response;
import org.example.database_final.service.InstructorService;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing instructors.
 */
@RestController
@RequestMapping(value = "/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    /**
     * Constructor for InstructorController.
     *
     * @param instructorService the service for handling instructor operations.
     */
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /**
     * Retrieves an instructor by their name.
     *
     * @param instructorName the name of the instructor to retrieve.
     * @return a ResponseEntity containing a Response object with the instructor data or an error message.
     */
    @GetMapping(value = "/get-instructor-by-name/{instructorName}")
    public ResponseEntity<Response> getInstructorByName(@PathVariable String instructorName) {
        try {
            List<InstructorDto> instructorDtoList = instructorService.getInstructorByName(instructorName);
            return ResponseEntity.ok(new Response("Successfully retrieved Instructor.", instructorDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to retrieve Instructor. " + exception.getMessage(), null));
        }
    }

    /**
     * Retrieves all instructors.
     *
     * @return a ResponseEntity containing a Response object with a list of all instructors or an error message.
     */
    @GetMapping("/get-all-instructors")
    public ResponseEntity<Response> getAllInstructor() {
        try {
            return ResponseEntity.ok(new Response("Successfully retrieved all Instructors.", instructorService.getAllInstructors()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find any Instructors. " + exception.getMessage(), null));
        }
    }

    /**
     * Creates a new instructor.
     *
     * @param request the DTO containing the new instructor data.
     * @return a ResponseEntity containing a Response object with the created instructor data or an error message.
     */
    @PostMapping(value = "/create-instructor")
    public ResponseEntity<Response> createInstructor(@RequestBody InstructorDto request) {
        try {
            InstructorDto instructorDto = instructorService.createInstructor(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Instructor.", instructorDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Instructor is not saved. " + exception.getMessage(), null));
        }
    }

    /**
     * Updates an existing instructor.
     *
     * @param request the DTO containing the updated instructor data.
     * @return a ResponseEntity containing a Response object with the updated instructor data or an error message.
     */
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
