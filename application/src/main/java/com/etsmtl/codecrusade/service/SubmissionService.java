package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Submission;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface SubmissionService {
	/**
	 * Finds submission by exercise id and submission id for authenticated user.
	 *
	 * @param exerciseId   the exercise id
	 * @param submissionId the submission id
	 * @return an Optional submission
	 */
	@PostAuthorize("hasPermission(returnObject, 'READ')")
	Optional<Submission> getSubmissionByExerciseForAuthenticatedUser(Integer exerciseId, Integer submissionId);

	/**
	 * Finds all submissions by exercise id.
	 *
	 * @param exerciseId the exercise id
	 * @return an Iterable of Submissions
	 */
	@PostFilter("hasRole('ADMIN') or hasPermission(filterObject, 'READ')")
	Iterable<Submission> getAllSubmissionsByExercise(Integer exerciseId);

	/**
	 * Creates a submission for provided exercise using arguments.
	 *
	 * @param exerciseId         the exercise id
	 * @param submission the submission
	 * @return the created submission, with a generated id if successful
	 * @throws UserNotAllowedException
	 * @throws ExerciseService.ExerciseNotFoundException
	 */
	@PreAuthorize("hasRole('USER')")
	Optional<Submission> createSubmissionForExercise(Integer exerciseId, Submission submission)
			throws UserNotAllowedException, ExerciseService.ExerciseNotFoundException;

	class UserNotAllowedException extends RuntimeException {

	}
}
