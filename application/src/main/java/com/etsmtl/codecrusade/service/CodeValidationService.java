package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.CodeValidationResults;

public interface CodeValidationService {
	/**
	 * Finds all execution results for given exercise and submission.
	 * @param exerciseId
	 * @return
	 */
	Iterable<CodeValidationResults> getAllResultsForExerciseAndSubmission(Integer exerciseId, Integer submissionId);
}
