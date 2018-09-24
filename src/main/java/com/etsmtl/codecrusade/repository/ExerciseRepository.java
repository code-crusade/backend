package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Exercise;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "exercises",
						path = "exercises")
public interface ExerciseRepository extends PagingAndSortingRepository<Exercise, Long> {
}
