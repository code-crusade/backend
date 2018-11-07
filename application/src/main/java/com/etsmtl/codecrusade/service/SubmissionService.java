package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Submission;

import java.util.Optional;

public interface SubmissionService {
	Optional<Submission> getSubmissionByExercise(Integer exerciseId, Integer submissionId);
}
