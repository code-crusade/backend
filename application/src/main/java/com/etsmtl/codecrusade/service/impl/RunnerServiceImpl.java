package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.CodeValidationResults;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.service.RunnerService;

import java.util.List;

public class RunnerServiceImpl implements RunnerService {

	private ExerciseRepository exerciseRepository;

	public RunnerServiceImpl(ExerciseRepository exerciseRepository){
		this.exerciseRepository = exerciseRepository;
	}

	@Override
	public List<CodeValidationResults> runArgumentsForExercise(Integer exerciseId,
			SubmissionArgument submissionArgument) {
		return null;
	}
}
