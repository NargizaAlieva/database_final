package org.example.database_final.service;


import org.example.database_final.dto.StudentDto;
import org.example.database_final.entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(Long id);
    Student getStudentEntityByName(String name);

    List<StudentDto> getStudentByName(String name);
    List<StudentDto> getStudentsByCourseId(Long courseId);
    List<StudentDto> getAllStudents();

    StudentDto createStudent(StudentDto instructorDto);
    StudentDto updateStudent(StudentDto instructorDto);
}
