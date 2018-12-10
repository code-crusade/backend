package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Interface for Student-Related operations
 */
@Service
public interface StudentService {
    @PreAuthorize("hasRole('ADMIN')")
    Iterable<Student> findAllStudents();
}
