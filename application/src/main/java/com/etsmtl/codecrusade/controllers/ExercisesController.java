package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.entities.Exercise;
import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.entities.embeddable.ReportResult;
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
import java.util.Map;
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
    public ResponseEntity<List<FixtureModel>> exercisesExerciseIdFixturesGet(Integer exerciseId) {
        return ResponseEntity.ok(exerciseService.findFixturesForExercise(exerciseId)
                                                .stream()
                                                .map(this::convertToDto)
                                                .collect(toList()));
    }

    @Override
    public ResponseEntity<ExerciseModel> exercisesExerciseIdGet(Integer exerciseId) {
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
    public ResponseEntity<ExerciseSubmissionModel> exercisesExerciseIdSubmissionsSubmissionIdGet(Integer exerciseId,
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
    public ResponseEntity<List<ExerciseModel>> exercisesIndex() {
        return ResponseEntity.ok(StreamSupport.stream(exerciseService.getAllExercises().spliterator(), false)
                                              .map(this::convertToEntity)
                                              .collect(toList()));
    }

    @Override
    public ResponseEntity<List<ExerciseSubmissionModel>> exercisesExerciseIdSubmissionsGet(Integer exerciseId) {
        return ResponseEntity.ok(
                StreamSupport.stream(submissionService.getAllSubmissionsByExercise(exerciseId).spliterator(), false)
                             .map(this::convertToEntity)
                             .collect(toList()));
    }

    @Override
    public ResponseEntity<Void> exercisesExerciseIdSubmissionsPost(Integer exerciseId,
                                                                   @Valid ExerciseSubmissionModel exerciseSubmission) {

        return submissionService.createSubmissionForExercise(exerciseId, convertToEntity(exerciseSubmission))
                                .map(created -> ResponseEntity.ok().<Void>build())
                                .orElse(ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<CodeValidationReportModel> exercisesExerciseIdSubmissionsSubmissionIdResultsGet(Integer exerciseId,
                                                                                                          Integer submissionId) {
        return codeValidationService.getReportForExerciseAndSubmission(exerciseId, submissionId)
                                    .map(this::convertToDto)
                                    .map(ResponseEntity::ok)
                                    .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<CodeValidationReportModel> exercisesExerciseIdTestPost(Integer exerciseId,
                                                                                 @Valid RunnerArgumentsModel runnerArguments) {
        return runnerService.runCodeForExercise(exerciseId, convertToEntity(runnerArguments))
                            .map(this::convertToDto)
                            .map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.ok().build());
    }

    @Override
    public ResponseEntity<ExerciseModel> exercisesAdd(@Valid ExerciseModel exercise) {
        return exerciseService.createExercise(convertToEntity(exercise))
                              .map(created -> ResponseEntity.created(UriComponentsBuilder.fromPath("/exercises/{id}")
                                                                                         .buildAndExpand(
                                                                                                 created.getId())
                                                                                         .toUri())
                                                            .body(convertToEntity(created)))
                              .orElse(ResponseEntity.badRequest().build());
    }

    private ExerciseModel convertToEntity(Exercise entity) {
        return modelMapper.map(entity, ExerciseModel.class);
    }

    private ExerciseSubmissionModel convertToEntity(Submission entity) {
        return modelMapper.map(entity, ExerciseSubmissionModel.class);
    }

    private SubmissionArgument convertToEntity(RunnerArgumentsModel dto) {
        return modelMapper.map(dto, SubmissionArgument.class);
    }

    private Submission convertToEntity(ExerciseSubmissionModel dto) {
        return modelMapper.map(dto, Submission.class);
    }

    private CodeValidationReportResultModel convertToDto(ReportResult entity) {
        return modelMapper.map(entity, CodeValidationReportResultModel.class);
    }

    private CodeValidationReportModel convertToDto(Report report) {
        return modelMapper.map(report, CodeValidationReportModel.class);
    }

    private Exercise convertToEntity(ExerciseModel exercise) {
        return modelMapper.map(exercise, Exercise.class);
    }

    private FixtureModel convertToDto(Map<String, String> fixture) {
        return modelMapper.map(fixture, FixtureModel.class);
    }
}
