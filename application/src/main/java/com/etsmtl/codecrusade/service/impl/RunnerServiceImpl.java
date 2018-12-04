package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.runner.Runner;
import com.etsmtl.codecrusade.service.RunnerService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RunnerServiceImpl implements RunnerService {

	private ExerciseRepository exerciseRepository;
	private Runner runner;

	public RunnerServiceImpl(ExerciseRepository exerciseRepository, Runner runner){
		this.exerciseRepository = exerciseRepository;
		this.runner = runner;
	}

	@Override
	public Optional<Report> runCodeForExercise(Integer exerciseId, SubmissionArgument argument) {
		return Optional.empty();
	}


}
