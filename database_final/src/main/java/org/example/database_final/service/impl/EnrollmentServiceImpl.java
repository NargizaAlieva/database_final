package org.example.database_final.service.impl;

import org.example.database_final.dto.EnrollmentDto;
import org.example.database_final.entity.Enrollment;
import org.example.database_final.repository.EnrollmentRepository;
import org.example.database_final.service.EnrollmentService;
import org.example.database_final.utils.exception.AlreadyExistException;
import org.example.database_final.utils.exception.ObjectNotFoundException;
import org.example.database_final.utils.mapper.EnrollmentMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing enrollments.
 */
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    /**
     * Constructor for EnrollmentServiceImpl.
     *
     * @param enrollmentRepository the repository for accessing enrollment data.
     * @param enrollmentMapper     the mapper for converting between Enrollment entities and DTOs.
     */
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    /**
     * Checks if an enrollment with the given ID exists.
     *
     * @param id the enrollment ID.
     * @return false (no return value used).
     * @throws AlreadyExistException if the enrollment ID already exists.
     */
    private boolean isIdExist(Long id) {
        if (enrollmentRepository.existsById(id))
            throw new AlreadyExistException("Enrollment", "id");
        return false;
    }

    /**
     * Saves an enrollment entity to the database.
     *
     * @param enrollment the enrollment entity to save.
     * @return the saved enrollment entity.
     */
    private Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    /**
     * Retrieves an enrollment by ID.
     *
     * @param id the ID of the enrollment.
     * @return the enrollment entity.
     * @throws ObjectNotFoundException if the enrollment with the given ID does not exist.
     */
    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Enrollment"));
    }

    /**
     * Retrieves enrollments by course ID.
     *
     * @param courseId the course ID.
     * @return a list of enrollment DTOs.
     */
    @Override
    public List<EnrollmentDto> getEnrollmentByCourseId(Long courseId) {
        return enrollmentMapper.entityToDtoList(enrollmentRepository.findByCourseId(courseId));
    }

    /**
     * Retrieves enrollments by student ID.
     *
     * @param studentId the student ID.
     * @return a list of enrollment DTOs.
     */
    @Override
    public List<EnrollmentDto> getEnrollmentByStudentId(Long studentId) {
        return enrollmentMapper.entityToDtoList(enrollmentRepository.findByStudentId(studentId));
    }

    /**
     * Retrieves all enrollments.
     *
     * @return a list of enrollment DTOs.
     */
    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentMapper.entityToDtoList(enrollmentRepository.findAll());
    }

    /**
     * Creates a new enrollment.
     *
     * @param enrollmentDtoRequest the enrollment DTO containing the new enrollment's details.
     * @return the created enrollment DTO.
     * @throws AlreadyExistException if an enrollment with the same name already exists.
     */
    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDtoRequest) {
        Enrollment newEnrollment = enrollmentMapper.dtoToEntity(enrollmentDtoRequest);
        newEnrollment.setCompletionStatus(false);
        return enrollmentMapper.entityToDto(save(newEnrollment));
    }

    /**
     * Deletes an enrollment by course ID and student name.
     *
     * @param courseId      the course ID.
     * @param studentName   the student name.
     */
    @Override
    public void deleteEnrollment(Long courseId, String studentName) {
        Enrollment enrollment = enrollmentRepository.findByStudentNameAndCourseId(studentName, courseId);
        enrollmentRepository.delete(enrollment);
    }

    /**
     * Sorts enrollments by enrollment date.
     *
     * @param enrollmentDtoList the list of enrollment DTOs.
     * @return a sorted list of enrollment DTOs by enrollment date.
     */
    @Override
    public List<EnrollmentDto> sortByEnrollmentDate(List<EnrollmentDto> enrollmentDtoList) {
        return enrollmentDtoList.stream()
                .sorted(Comparator.comparing(EnrollmentDto::getEnrollmentDate))
                .collect(Collectors.toList());
    }

    /**
     * Sorts enrollments by completion status.
     *
     * @param completionStatus  the completion status to filter by.
     * @param enrollmentDtoList the list of enrollment DTOs.
     * @return a filtered list of enrollment DTOs matching the completion status.
     */
    @Override
    public List<EnrollmentDto> sortByCompletionStatus(Boolean completionStatus, List<EnrollmentDto> enrollmentDtoList) {
        return enrollmentDtoList.stream()
                .filter(enrollment -> enrollment.getCompletionStatus().equals(completionStatus))
                .collect(Collectors.toList());
    }
}

