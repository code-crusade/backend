package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.service.RunnerService;
import org.springframework.stereotype.Component;

@Component
public class RunnerServiceImpl implements RunnerService {

	private ExerciseRepository exerciseRepository;

	public RunnerServiceImpl(ExerciseRepository exerciseRepository){
		this.exerciseRepository = exerciseRepository;
	}

}
