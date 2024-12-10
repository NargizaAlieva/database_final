package org.example.database_final.utils.mapper;

import org.example.database_final.dto.InstructorDto;
import org.example.database_final.entity.Instructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between `Instructor` entities and `InstructorDto` data transfer objects (DTOs).
 * This class provides methods to transform instructor data from the entity layer to the DTO layer and vice versa.
 */
@Component
public class InstructorMapper {

    /**
     * Converts an `Instructor` entity to an `InstructorDto`.
     *
     * @param instructor The `Instructor` entity to be converted.
     * @return The corresponding `InstructorDto`.
     */
    public InstructorDto entityToDto(Instructor instructor) {
        if (instructor == null) {
            return null;
        }
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(instructor.getId());
        instructorDto.setName(instructor.getName());
        instructorDto.setEmail(instructor.getEmail());
        instructorDto.setBio(instructor.getBio());
        instructorDto.setRegistrationDate(instructor.getRegistrationDate());
        return instructorDto;
    }

    /**
     * Converts a list of `Instructor` entities to a list of `InstructorDto` objects.
     *
     * @param instructors The list of `Instructor` entities to be converted.
     * @return A list of `InstructorDto` objects.
     */
    public List<InstructorDto> entityToDtoList(List<Instructor> instructors) {
        if (instructors == null) {
            return null;
        }
        return instructors.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    /**
     * Converts an `InstructorDto` to an `Instructor` entity.
     *
     * @param instructorDto The `InstructorDto` to be converted.
     * @return The corresponding `Instructor` entity.
     */
    public Instructor dtoToEntity(InstructorDto instructorDto) {
        if (instructorDto == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setId(instructorDto.getId());
        instructor.setName(instructorDto.getName());
        instructor.setEmail(instructorDto.getEmail());
        instructor.setBio(instructorDto.getBio());
        return instructor;
    }
}
