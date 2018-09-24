package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Submission;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "submissions",
						path = "submissions")
public interface SubmissionRepository extends PagingAndSortingRepository<Submission, Long> {
}
