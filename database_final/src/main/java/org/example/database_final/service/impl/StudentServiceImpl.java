package org.example.database_final.service.impl;

import org.example.database_final.dto.StudentDto;
import org.example.database_final.entity.Student;
import org.example.database_final.repository.StudentRepository;
import org.example.database_final.service.StudentService;
import org.example.database_final.utils.exception.AlreadyExistException;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.example.database_final.utils.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing students.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /**
     * Constructor for StudentServiceImpl.
     *
     * @param studentRepository the repository for accessing student data.
     * @param studentMapper     the mapper for converting between Student entities and DTOs.
     */
    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * Checks if a student ID exists in the repository.
     *
     * @param id the student ID.
     * @throws ObjectNotFoundException if the student ID does not exist.
     */
    private void isIdExist(Long id) {
        if (!studentRepository.existsById(id))
            throw new ObjectNotFoundException("Student");
    }

    /**
     * Checks if a student email already exists.
     *
     * @param email the student email.
     * @throws AlreadyExistException if the student email already exists.
     */
    private Boolean isEmailExist(String email) {
        if (studentRepository.isEmailExist(email))
            throw new AlreadyExistException("Student", "email");
        return false;
    }

    /**
     * Saves a student entity to the database.
     *
     * @param student the student entity to save.
     * @return the saved student entity.
     */
    private Student save(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Retrieves a student by its ID.
     *
     * @param id the ID of the student.
     * @return the student entity.
     * @throws ObjectNotFoundException if the student with the given ID does not exist.
     */
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Student"));
    }

    /**
     * Retrieves a student entity by name.
     *
     * @param name the name of the student.
     * @return the student entity.
     * @throws ObjectNotFoundException if the student with the given name does not exist.
     */
    @Override
    public Student getStudentEntityByName(String name) {
        return studentRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Student"));
    }

    /**
     * Retrieves students by name.
     *
     * @param name the name to search for.
     * @return a list of student DTOs.
     */
    @Override
    public List<StudentDto> getStudentByName(String name) {
        return studentMapper.entityToDtoList(studentRepository.findByNameContains(name));
    }

    /**
     * Retrieves students by course ID.
     *
     * @param courseId the course ID.
     * @return a list of student DTOs.
     */
    @Override
    public List<StudentDto> getStudentsByCourseId(Long courseId) {
        return studentMapper.entityToDtoList(studentRepository.findStudentsByCourseId(courseId));
    }

    /**
     * Retrieves all students.
     *
     * @return a list of student DTOs.
     */
    @Override
    public List<StudentDto> getAllStudents() {
        return studentMapper.entityToDtoList(studentRepository.findAll());
    }

    /**
     * Creates a new student.
     *
     * @param studentDto the student DTO containing the new student's details.
     * @return the created student DTO or null if the email already exists.
     */
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        if (!isEmailExist(studentDto.getEmail())) // Check if the email already exists
            return studentMapper.entityToDto(save(studentMapper.dtoToEntity(studentDto)));
        return null;
    }

    /**
     * Updates an existing student.
     *
     * @param studentDto the student DTO containing the updated details.
     * @return the updated student DTO.
     */
    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        isIdExist(studentDto.getId()); // Ensure the student ID exists
        Student oldStudent = studentMapper.dtoToEntity(studentDto);
        Student newStudent = getStudentById(studentDto.getId());

        // Update only non-null fields from the request DTO to the existing student entity
        if (!newStudent.getEmail().equals(oldStudent.getEmail()))
            isEmailExist(oldStudent.getEmail());

        if (oldStudent.getName() != null)
            newStudent.setName(oldStudent.getName());
        if (oldStudent.getEmail() != null)
            newStudent.setEmail(oldStudent.getEmail());
        if (oldStudent.getDateOfBirth() != null)
            newStudent.setDateOfBirth(oldStudent.getDateOfBirth());

        return studentMapper.entityToDto(save(newStudent));
    }

    /**
     * Sorts students by name.
     *
     * @return a list of student DTO responses sorted by name.
     */
    @Override
    public List<StudentDto> sortByName() {
        return getAllStudents().stream()
                .sorted(Comparator.comparing(StudentDto::getName))
                .collect(Collectors.toList());
    }
}
