package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Submission;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmissionRepository extends PagingAndSortingRepository<Submission, Long> {

	/**
	 * Finds a submission by exercise and id.
	 *
	 * @param exerciseId the exercise
	 * @param id the submission id
	 * @return
	 */
	Optional<Submission> findByExercise_IdAndId(Integer exerciseId, Integer id);
}
