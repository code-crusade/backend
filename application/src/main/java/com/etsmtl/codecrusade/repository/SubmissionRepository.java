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
	 * @param id         the submission id
	 * @return an Optional of a submission
	 */
	Optional<Submission> findByExercise_IdAndId(Integer exerciseId, Integer id);

	/**
	 * Finds submissions by exercise id.
	 *
	 * @param id the exercise id
	 * @return an iterable of submissions
	 */
	Iterable<Submission> findAllByExercise_Id(Integer id);

	/**
	 * Counts submissions by given user on provided exercise.
	 * @param userName the username
	 * @param id the exercise id
	 * @return the count of submissions
	 */
	long countByExercise_IdAndUser_Id(Integer id, Integer userName);
}
