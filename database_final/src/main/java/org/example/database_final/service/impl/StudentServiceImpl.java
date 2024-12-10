package org.example.database_final.service.impl;

import org.example.database_final.dto.StudentDto;
import org.example.database_final.entity.Student;
import org.example.database_final.repository.StudentRepository;
import org.example.database_final.service.StudentService;
import org.example.database_final.utils.exception.AlreadyExistException;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.example.database_final.utils.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    private void isIdExist(Long id) {
        if (!studentRepository.existsById(id))
            throw new ObjectNotFoundException("Student");
    }

    private Boolean isEmailExist(String name) {
        if (studentRepository.isEmailExist(name))
            throw new AlreadyExistException("Student", "email");
        return false;
    }

    private Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Student"));
    }

    @Override
    public Student getStudentEntityByName(String name) {
        return studentRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Student"));
    }

    @Override
    public List<StudentDto> getStudentByName(String name) {
        return studentMapper.entityToDtoList(studentRepository.findByNameContains(name));
    }

    @Override
    public List<StudentDto> getStudentsByCourseId(Long courseId) {
        return studentMapper.entityToDtoList(studentRepository.findStudentsByCourseId(courseId));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentMapper.entityToDtoList(studentRepository.findAll());
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        if (!isEmailExist(studentDto.getName()))
            return studentMapper.entityToDto(save(studentMapper.dtoToEntity(studentDto)));
        return null;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        isIdExist(studentDto.getId());
        Student oldStudent = studentMapper.dtoToEntity(studentDto);
        Student newStudent = getStudentById(studentDto.getId());

        if (!newStudent.getEmail().equals(oldStudent.getEmail()))
            isEmailExist(oldStudent.getEmail());

        if (!(oldStudent.getName() == null))
            newStudent.setName(oldStudent.getName());
        if (!(oldStudent.getEmail() == null))
            newStudent.setEmail(oldStudent.getEmail());
        if (!(oldStudent.getDateOfBirth() == null))
            newStudent.setDateOfBirth(oldStudent.getDateOfBirth());

        return studentMapper.entityToDto(save(newStudent));
    }
}
