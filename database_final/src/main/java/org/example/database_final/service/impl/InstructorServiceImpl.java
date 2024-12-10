package org.example.database_final.service.impl;

import org.example.database_final.dto.InstructorDto;
import org.example.database_final.entity.Instructor;
import org.example.database_final.repository.InstructorRepository;
import org.example.database_final.service.InstructorService;
import org.example.database_final.utils.exception.AlreadyExistException;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.example.database_final.utils.mapper.InstructorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing instructors.
 */
@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    /**
     * Constructor for InstructorServiceImpl.
     *
     * @param instructorRepository the repository for accessing instructor data.
     * @param instructorMapper     the mapper for converting between Instructor entities and DTOs.
     */
    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    /**
     * Checks if an instructor with the given ID exists.
     *
     * @param id the instructor ID.
     * @return false (no return value used).
     * @throws AlreadyExistException if the instructor ID already exists.
     */
    private boolean isIdExist(Long id) {
        if (instructorRepository.existsById(id)) {
            throw new AlreadyExistException("Instructor", "id");
        }
        return false;
    }

    /**
     * Checks if an instructor with the given email exists.
     *
     * @param name the instructor email.
     * @return false (no return value used).
     * @throws AlreadyExistException if the instructor email already exists.
     */
    private boolean isEmailExist(String name) {
        if (instructorRepository.isEmailExist(name)) {
            throw new AlreadyExistException("Instructor", "email");
        }
        return false;
    }

    /**
     * Saves an instructor entity to the database.
     *
     * @param instructor the instructor entity to save.
     * @return the saved instructor entity.
     */
    private Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    /**
     * Retrieves an instructor by ID.
     *
     * @param id the ID of the instructor.
     * @return the instructor entity.
     * @throws ObjectNotFoundException if the instructor with the given ID does not exist.
     */
    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Instructor"));
    }

    /**
     * Retrieves an instructor entity by name.
     *
     * @param name the name of the instructor.
     * @return the instructor entity.
     * @throws ObjectNotFoundException if the instructor with the given name does not exist.
     */
    @Override
    public Instructor getInstructorEntityByName(String name) {
        return instructorRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Instructor"));
    }

    /**
     * Retrieves a list of instructor DTOs whose names contain the given string.
     *
     * @param name the string to search by.
     * @return a list of instructor DTOs.
     */
    @Override
    public List<InstructorDto> getInstructorByName(String name) {
        return instructorMapper.entityToDtoList(instructorRepository.findByNameContains(name));
    }

    /**
     * Retrieves a list of all instructors.
     *
     * @return a list of instructor DTOs.
     */
    @Override
    public List<InstructorDto> getAllInstructors() {
        return instructorMapper.entityToDtoList(instructorRepository.findAll());
    }

    /**
     * Creates a new instructor.
     *
     * @param instructorDto the instructor DTO containing the new instructor's details.
     * @return the created instructor DTO.
     * @throws AlreadyExistException if an instructor with the same email already exists.
     */
    @Override
    public InstructorDto createInstructor(InstructorDto instructorDto) {
        if (!isEmailExist(instructorDto.getName())) {
            return instructorMapper.entityToDto(save(instructorMapper.dtoToEntity(instructorDto)));
        }
        return null;
    }

    /**
     * Updates an existing instructor.
     *
     * @param instructorDto the instructor DTO containing the updated details.
     * @return the updated instructor DTO.
     * @throws ObjectNotFoundException if the instructor with the given ID does not exist.
     * @throws AlreadyExistException  if the new email provided for the instructor already exists.
     */
    @Override
    public InstructorDto updateInstructor(InstructorDto instructorDto) {
        Instructor oldInstructor = instructorMapper.dtoToEntity(instructorDto);
        Instructor newInstructor = getInstructorById(instructorDto.getId());

        // Check if the email has been changed to avoid conflicts with existing emails.
        if (!newInstructor.getEmail().equals(oldInstructor.getEmail())) {
            isEmailExist(oldInstructor.getEmail());
        }

        // Update instructor details if new values are provided.
        if (!(oldInstructor.getName() == null)) {
            newInstructor.setName(oldInstructor.getName());
        }
        if (!(oldInstructor.getEmail() == null)) {
            newInstructor.setEmail(oldInstructor.getEmail());
        }
        if (!(oldInstructor.getBio() == null)) {
            newInstructor.setBio(oldInstructor.getBio());
        }

        return instructorMapper.entityToDto(save(newInstructor));
    }
}
