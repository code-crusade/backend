package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.ClassGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "classes",
						path = "classes")
public interface ClassGroupRepository extends PagingAndSortingRepository<ClassGroup, Long> {
}
