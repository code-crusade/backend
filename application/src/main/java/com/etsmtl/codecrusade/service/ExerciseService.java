package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Exercise;

import java.util.Optional;

public interface ExerciseService {
	Optional<Exercise> getExerciseFromId(Integer id);

	Iterable<Exercise> getAllExercises();

	class ExerciseNotFoundException extends Exception {

	}
}
