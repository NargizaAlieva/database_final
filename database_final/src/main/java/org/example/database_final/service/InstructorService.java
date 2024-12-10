package org.example.database_final.service;

import org.example.database_final.dto.InstructorDto;
import org.example.database_final.entity.Instructor;

import java.util.List;

public interface InstructorService {
    Instructor getInstructorById(Long id);
    Instructor getInstructorEntityByName(String name);

    List<InstructorDto> getInstructorByName(String name);
    List<InstructorDto> getAllInstructors();

    InstructorDto createInstructor(InstructorDto instructorDto);
    InstructorDto updateInstructor(InstructorDto instructorDto);
}
