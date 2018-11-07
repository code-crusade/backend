package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.entities.CodeValidationResults;
import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.model.*;
import com.etsmtl.codecrusade.service.CodeValidationService;
import com.etsmtl.codecrusade.service.ExerciseService;
import com.etsmtl.codecrusade.service.SubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
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

	private final CodeValidationService codeValidationService;
	private       SubmissionService     submissionService;
	private       ExerciseService       exerciseService;
	private       ModelMapper           modelMapper;

	@Autowired
	public ExercisesController(ExerciseService exerciseService, SubmissionService submissionService,
			CodeValidationService codeValidationService, ModelMapper modelMapper) {
		this.exerciseService = exerciseService;
		this.submissionService = submissionService;
		this.modelMapper = modelMapper;
		this.codeValidationService = codeValidationService;
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

	@Override
	public ResponseEntity<List<ExerciseSubmission>> exercisesExerciseIdSubmissionsGet(Integer exerciseId) {
		return ResponseEntity.ok(
				StreamSupport.stream(submissionService.getAllSubmissionsByExercise(exerciseId).spliterator(), false)
							 .map(this::convertToDto)
							 .collect(toList()));
	}

	@Override
	public ResponseEntity<Void> exercisesExerciseIdSubmissionsPost(Integer exerciseId,
			@Valid RunnerArguments runnerArguments) {
		return submissionService.createSubmissionForExercise(exerciseId, convertToEntity(runnerArguments))
								.map(created -> ResponseEntity.ok().<Void>build())
								.orElse(ResponseEntity.badRequest().build());
	}

	@Override
	public ResponseEntity<CodeValidationReport> exercisesExerciseIdSubmissionsSubmissionIdResultsGet(Integer exerciseId,
			Integer submissionId) {
		List<CodeValidationReportResults> results = StreamSupport.stream(
				codeValidationService.getAllResultsForExerciseAndSubmission(exerciseId, submissionId).spliterator(),
				false).map(this::convertToDto).collect(toList());
		return ResponseEntity.ok(new CodeValidationReport().results(results).exerciseId(exerciseId));
	}

	@Override
	public ResponseEntity<CodeValidationReport> exercisesExerciseIdTestPost(Integer exerciseId,
			@Valid RunnerArguments runnerArguments) {
		return null;
	}

	private Exercise convertToDto(com.etsmtl.codecrusade.entities.Exercise entity) {
		return modelMapper.map(entity, Exercise.class);
	}

	private ExerciseSubmission convertToDto(Submission entity) {
		return modelMapper.map(entity, ExerciseSubmission.class);
	}

	private SubmissionArgument convertToEntity(RunnerArguments dto) {
		return modelMapper.map(dto, SubmissionArgument.class);
	}

	private CodeValidationReportResults convertToDto(CodeValidationResults dto) {
		return modelMapper.map(dto, CodeValidationReportResults.class);
	}
}
