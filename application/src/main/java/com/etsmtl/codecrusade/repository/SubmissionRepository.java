package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Submission;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends PagingAndSortingRepository<Submission, Long> {
}
