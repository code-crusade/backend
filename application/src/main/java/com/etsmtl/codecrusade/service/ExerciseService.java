package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Exercise;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ExerciseService {
	Optional<Exercise> getExerciseFromId(@NotNull Integer id);

	Iterable<Exercise> getAllExercises();

	Optional<Exercise> createExercise(@NotNull Exercise exercise);

	class ExerciseNotFoundException extends RuntimeException {

	}
}
