package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.CodeValidationResults;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;

import java.util.List;

public interface RunnerService {
	List<CodeValidationResults> runArgumentsForExercise(Integer exerciseId, SubmissionArgument submissionArgument);
}
