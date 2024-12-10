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

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    private boolean isIdExist(Long id) {
        if (enrollmentRepository.existsById(id))
            throw new AlreadyExistException("Enrollment", "id");
        return false;
    }

    private Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
         return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Enrollment"));
    }

    @Override
    public List<EnrollmentDto> getEnrollmentByCourseId(Long courseId) {
        return enrollmentMapper.entityToDtoList(enrollmentRepository.findByCourseId(courseId));
    }

    @Override
    public List<EnrollmentDto> getEnrollmentByStudentId(Long studentId) {
        return enrollmentMapper.entityToDtoList(enrollmentRepository.findByStudentId(studentId));
    }

    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentMapper.entityToDtoList(enrollmentRepository.findAll());
    }

    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDtoRequest) {
        Enrollment newEnrollment = enrollmentMapper.dtoToEntity(enrollmentDtoRequest);
        newEnrollment.setCompletionStatus(false);
        return enrollmentMapper.entityToDto(save(newEnrollment));
    }

    @Override
    public List<EnrollmentDto> sortByEnrollmentDate(List<EnrollmentDto> enrollmentDtoList) {
        return enrollmentDtoList.stream()
                .sorted(Comparator.comparing(EnrollmentDto::getEnrollmentDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDto> sortByCompletionStatus(Boolean completionStatus, List<EnrollmentDto> enrollmentDtoList) {
        return enrollmentDtoList.stream()
                .filter(enrollment -> enrollment.getCompletionStatus().equals(completionStatus))
                .collect(Collectors.toList());
    }
}
