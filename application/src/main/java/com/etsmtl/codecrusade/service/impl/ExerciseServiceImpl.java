package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Exercise;
import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

	private ExerciseRepository exerciseRepository;

	@Autowired
	public ExerciseServiceImpl(ExerciseRepository exerciseRepository){
		this.exerciseRepository = exerciseRepository;
	}

	@Override
	public Optional<Exercise> getExerciseFromId(Integer id) {
		return exerciseRepository.findById(id);
	}

	@Override
	public Iterable<Exercise> getAllExercises() {
		return exerciseRepository.findAll();
	}

}
