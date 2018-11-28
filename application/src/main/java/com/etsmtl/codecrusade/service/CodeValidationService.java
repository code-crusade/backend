package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Report;

import java.util.Optional;

public interface CodeValidationService {
	/**
	 * Finds all execution results for given exercise and submission.
	 * @param exerciseId
	 * @return
	 */
	Optional<Report> getReportForExerciseAndSubmission(Integer exerciseId, Integer submissionId);
}
