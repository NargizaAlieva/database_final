package org.example.database_final.utils.mapper;

import org.example.database_final.dto.StudentDto;
import org.example.database_final.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between `Student` entities and `StudentDto` data transfer objects (DTOs).
 * This class provides methods to transform student data from the entity layer to the DTO layer and vice versa.
 */
@Component
public class StudentMapper {
    /**
     * Converts a `Student` entity to a `StudentDto`.
     *
     * @param student The `Student` entity to be converted.
     * @return The corresponding `StudentDto`.
     */
    public StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setRegistrationDate(student.getRegistrationDate());
        return studentDto;
    }

    /**
     * Converts a list of `Student` entities to a list of `StudentDto` objects.
     *
     * @param students The list of `Student` entities to be converted.
     * @return A list of `StudentDto` objects.
     */
    public List<StudentDto> entityToDtoList(List<Student> students) {
        return students.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    /**
     * Converts a `StudentDto` to a `Student` entity.
     *
     * @param studentDto The `StudentDto` to be converted.
     * @return The corresponding `Student` entity.
     */
    public Student dtoToEntity (StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        return student;
    }
}
