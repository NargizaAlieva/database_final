package org.example.database_final.utils.mapper;

import org.example.dp_project.dto.InstructorDto;
import org.example.dp_project.entity.Instructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstructorMapper {
    public InstructorDto entityToDto(Instructor instructor) {
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(instructor.getId());
        instructorDto.setName(instructor.getName());
        instructorDto.setEmail(instructor.getEmail());
        instructorDto.setBio(instructor.getBio());
        instructorDto.setRegistrationDate(instructor.getRegistrationDate());
        return instructorDto;
    }

    public List<InstructorDto> entityToDtoList(List<Instructor> instructors) {
        return instructors.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Instructor dtoToEntity (InstructorDto instructorDto) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorDto.getId());
        instructor.setName(instructorDto.getName());
        instructor.setEmail(instructorDto.getEmail());
        instructor.setBio(instructorDto.getBio());
        return instructor;
    }
}
