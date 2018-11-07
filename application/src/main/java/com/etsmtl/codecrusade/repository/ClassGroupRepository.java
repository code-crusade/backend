package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.ClassGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends PagingAndSortingRepository<ClassGroup, Long> {
}
