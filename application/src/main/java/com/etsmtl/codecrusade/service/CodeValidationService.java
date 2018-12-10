package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Report;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

public interface CodeValidationService {
	/**
	 * Finds all execution results for given exercise and submission.
	 *
	 * @param exerciseId
	 * @return
	 */
	@PostAuthorize("hasRole('ADMIN') or hasPermission(returnObject, 'READ')")
	Optional<Report> getReportForExerciseAndSubmission(Integer exerciseId, Integer submissionId);
}
