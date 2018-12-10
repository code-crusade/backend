package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Student;
import com.etsmtl.codecrusade.repository.StudentRepository;
import com.etsmtl.codecrusade.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Student service operations.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}
