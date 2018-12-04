package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Exercise;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ExerciseService {

    @PreAuthorize("hasRole('USER')")
    Optional<Exercise> getExerciseFromId(@NotNull Integer id);

    @PreAuthorize("hasRole('USER')")
    Iterable<Exercise> getAllExercises();

    @PreAuthorize("hasRole('ADMIN') or hasPermission(#exercise,'WRITE')")
    Optional<Exercise> createExercise(@NotNull Exercise exercise);

    class ExerciseNotFoundException extends RuntimeException {

    }
}
