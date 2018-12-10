package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.entities.embeddable.ReportResult;
import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.model.*;
import com.etsmtl.codecrusade.service.CodeValidationService;
import com.etsmtl.codecrusade.service.ExerciseService;
import com.etsmtl.codecrusade.service.RunnerService;
import com.etsmtl.codecrusade.service.SubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.UriComponentsBuilder;

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

    private CodeValidationService codeValidationService;
    private RunnerService runnerService;
    private SubmissionService submissionService;
    private ExerciseService exerciseService;
    private ModelMapper modelMapper;

    @Autowired
    public ExercisesController(ExerciseService exerciseService, SubmissionService submissionService,
                               CodeValidationService codeValidationService, RunnerService runnerService,
                               ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.submissionService = submissionService;
        this.modelMapper = modelMapper;
        this.codeValidationService = codeValidationService;
        this.runnerService = runnerService;
    }

    @Override
    public ResponseEntity<List<Fixture>> exercisesExerciseIdFixturesGet(Integer exerciseId) {
        return null;
    }

    @Override
    public ResponseEntity<Exercise> exercisesExerciseIdGet(Integer exerciseId) {
        if (exerciseId != null) {
            return exerciseService.getExerciseFromId(exerciseId)
                                  .map(this::convertToEntity)
                                  .map(ResponseEntity::ok)
                                  .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<ExerciseSubmission> exercisesExerciseIdSubmissionsSubmissionIdGet(Integer exerciseId,
                                                                                            Integer submissionId) {
        if (exerciseId != null && submissionId != null) {
            return submissionService.getSubmissionByExerciseForAuthenticatedUser(exerciseId, submissionId)
                                    .map(this::convertToEntity)
                                    .map(ResponseEntity::ok)
                                    .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Exercise>> exercisesIndex() {
        return ResponseEntity.ok(StreamSupport.stream(exerciseService.getAllExercises().spliterator(), false)
                                              .map(this::convertToEntity)
                                              .collect(toList()));
    }

    @Override
    public ResponseEntity<List<ExerciseSubmission>> exercisesExerciseIdSubmissionsGet(Integer exerciseId) {
        return ResponseEntity.ok(
                StreamSupport.stream(submissionService.getAllSubmissionsByExercise(exerciseId).spliterator(), false)
                             .map(this::convertToEntity)
                             .collect(toList()));
    }

    @Override
    public ResponseEntity<Void> exercisesExerciseIdSubmissionsPost(Integer exerciseId,
                                                                   @Valid ExerciseSubmission exerciseSubmission) {

        return submissionService.createSubmissionForExercise(exerciseId, convertToEntity(exerciseSubmission))
                                .map(created -> ResponseEntity.ok().<Void>build())
                                .orElse(ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<CodeValidationReport> exercisesExerciseIdSubmissionsSubmissionIdResultsGet(Integer exerciseId,
                                                                                                     Integer submissionId) {
        return codeValidationService.getReportForExerciseAndSubmission(exerciseId, submissionId)
                                    .map(this::convertToDto)
                                    .map(ResponseEntity::ok)
                                    .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<CodeValidationReport> exercisesExerciseIdTestPost(Integer exerciseId,
                                                                            @Valid RunnerArguments runnerArguments) {
        return runnerService.runCodeForExercise(exerciseId, convertToEntity(runnerArguments))
                            .map(this::convertToDto)
                            .map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.ok().build());
    }

    @Override
    public ResponseEntity<Exercise> exercisesAdd(@Valid Exercise exercise) {
        return exerciseService.createExercise(convertToEntity(exercise))
                              .map(created -> ResponseEntity.created(UriComponentsBuilder.fromPath("/exercises/{id}")
                                                                                         .buildAndExpand(
                                                                                                 created.getId())
                                                                                         .toUri())
                                                            .body(convertToEntity(created)))
                              .orElse(ResponseEntity.badRequest().build());
    }

    private Exercise convertToEntity(com.etsmtl.codecrusade.entities.Exercise entity) {
        return modelMapper.map(entity, Exercise.class);
    }

    private ExerciseSubmission convertToEntity(Submission entity) {
        return modelMapper.map(entity, ExerciseSubmission.class);
    }

    private SubmissionArgument convertToEntity(RunnerArguments dto) {
        return modelMapper.map(dto, SubmissionArgument.class);
    }

    private Submission convertToEntity(ExerciseSubmission dto) {
        return modelMapper.map(dto, Submission.class);
    }

    private CodeValidationReportResult convertToDto(ReportResult dto) {
        return modelMapper.map(dto, CodeValidationReportResult.class);
    }

    private CodeValidationReport convertToDto(Report report) {
        return modelMapper.map(report, CodeValidationReport.class);
    }

    private com.etsmtl.codecrusade.entities.Exercise convertToEntity(Exercise exercise) {
        return modelMapper.map(exercise, com.etsmtl.codecrusade.entities.Exercise.class);
    }
}
