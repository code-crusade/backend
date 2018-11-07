package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Exercise;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends PagingAndSortingRepository<Exercise, Integer> {
}
