package org.example.database_final.service;

import org.example.database_final.dto.InstructorDto;
import org.example.database_final.entity.Instructor;

import java.util.List;

/**
 * Service interface for managing `Instructor` entities.
 * This service provides methods for retrieving, creating, and updating instructor data.
 */
public interface InstructorService {

    /**
     * Retrieves an `Instructor` by their unique ID.
     *
     * @param id The unique identifier of the instructor.
     * @return The `Instructor` entity corresponding to the provided ID.
     */
    Instructor getInstructorById(Long id);

    /**
     * Retrieves an `Instructor` entity by their name.
     *
     * @param name The name of the instructor.
     * @return The `Instructor` entity corresponding to the provided name.
     */
    Instructor getInstructorEntityByName(String name);

    /**
     * Retrieves a list of `InstructorDto` objects by name.
     *
     * @param name The name of the instructors to search for.
     * @return A list of `InstructorDto` objects corresponding to the provided name.
     */
    List<InstructorDto> getInstructorByName(String name);

    /**
     * Retrieves all instructors.
     *
     * @return A list of `InstructorDto` objects representing all instructors.
     */
    List<InstructorDto> getAllInstructors();

    /**
     * Creates a new instructor.
     *
     * @param instructorDto The data transfer object containing the details of the instructor to be created.
     * @return The created `InstructorDto` object.
     */
    InstructorDto createInstructor(InstructorDto instructorDto);

    /**
     * Updates an existing instructor.
     *
     * @param instructorDto The data transfer object containing the updated details of the instructor.
     * @return The updated `InstructorDto` object.
     */
    InstructorDto updateInstructor(InstructorDto instructorDto);

    /**
     * Sorts instructors by name.
     *
     * @return A list of `InstructorDto` objects sorted by name.
     */
    List<InstructorDto> sortByName();
}
