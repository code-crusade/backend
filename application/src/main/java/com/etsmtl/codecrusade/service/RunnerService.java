package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;

import java.util.Optional;

public interface RunnerService {
    Optional<Report> runCodeForExercise(Integer exerciseId, SubmissionArgument argument);
}
