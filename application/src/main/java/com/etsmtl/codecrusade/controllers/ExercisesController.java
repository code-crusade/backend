package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.model.Exercise;
import com.etsmtl.codecrusade.model.ExerciseSubmission;
import com.etsmtl.codecrusade.service.ExerciseService;
import com.etsmtl.codecrusade.service.SubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Controller for the exercises endpoints
 * <p>
 * TODO : handle most error responses
 *
 * @author ak90280
 */
@Controller
public class ExercisesController implements ExercisesApi {

	private SubmissionService submissionService;
	private ExerciseService   exerciseService;
	private ModelMapper       modelMapper;

	@Autowired
	public ExercisesController(ExerciseService exerciseService, SubmissionService submissionService,
			ModelMapper modelMapper) {
		this.exerciseService = exerciseService;
		this.submissionService = submissionService;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<Exercise> exercisesExerciseIdGet(Integer exerciseId) {
		if (exerciseId != null) {
			return exerciseService.getExerciseFromId(exerciseId)
								  .map(this::convertToDto)
								  .map(ResponseEntity::ok)
								  .orElse(ResponseEntity.notFound().build());
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<ExerciseSubmission> exercisesExerciseIdSubmissionsSubmissionIdGet(Integer exerciseId,
			Integer submissionId) {
		if (exerciseId != null && submissionId != null) {
			return submissionService.getSubmissionByExercise(exerciseId, submissionId)
									.map(this::convertToDto)
									.map(ResponseEntity::ok)
									.orElse(ResponseEntity.notFound().build());
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<List<Exercise>> exercisesIndex() {
		return ResponseEntity.ok(StreamSupport.stream(exerciseService.getAllExercises().spliterator(), false)
											  .map(this::convertToDto)
											  .collect(toList()));
	}

	private Exercise convertToDto(com.etsmtl.codecrusade.entities.Exercise entity) {
		return modelMapper.map(entity, Exercise.class);
	}

	private ExerciseSubmission convertToDto(Submission entity) {
		return modelMapper.map(entity, ExerciseSubmission.class);
	}
}
