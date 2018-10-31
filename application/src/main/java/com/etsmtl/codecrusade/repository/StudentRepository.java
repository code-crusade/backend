package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "students",
						path = "students")
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
