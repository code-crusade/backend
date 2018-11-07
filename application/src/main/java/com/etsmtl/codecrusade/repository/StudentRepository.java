package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
