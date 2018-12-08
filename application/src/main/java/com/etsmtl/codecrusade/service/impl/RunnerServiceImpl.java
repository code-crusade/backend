package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.AssertionResult;
import com.etsmtl.codecrusade.entities.AssertionResultItem;
import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.entities.TestCaseResult;
import com.etsmtl.codecrusade.entities.embeddable.ReportResult;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.runner.Runner;
import com.etsmtl.codecrusade.runner.RunnerResult;
import com.etsmtl.codecrusade.runner.RunnerResultItem;
import com.etsmtl.codecrusade.service.RunnerService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RunnerServiceImpl implements RunnerService {

	private ExerciseRepository exerciseRepository;
	private Runner runner;
    private Report report;

	public RunnerServiceImpl(ExerciseRepository exerciseRepository, Runner runner){
		this.exerciseRepository = exerciseRepository;
		this.runner = runner;
	}

	@Override
	public Optional<Report> runCodeForExercise(Integer exerciseId, SubmissionArgument argument) {
        this.exerciseRepository.findById(exerciseId).ifPresent(exercise -> {
            Runner.Language lang = Runner.Language.valueOf(argument.getLanguage().toUpperCase());

            RunnerResult result = this.runner.run(lang, 60000, argument.getCode(),
                    exercise.getFixtures().get(argument.getLanguage()));

            this.report = Report
                    .builder()
                    .exitCode(result.getExitCode())
                    .executionTime(Long.valueOf(result.getExecutionTime()).intValue())
                    .exercise(exercise)
                    .stderr(result.isError() ? result.getOutput() : "")
                    .stdout(!result.isError() ? result.getOutput() : "")
                    .timedOut(result.isTimeout())
                    .result(ReportResult
                            .builder()
                            .completed(result.isSuccess())
                            .output(Collections.singletonList(TestCaseResult.builder()
                                                                            .passed(result.isSuccess())
                                                                            .text("")
                                                                            .items(result.getItems()
                                                                                         .stream()
                                                                                         .map(RunnerServiceImpl::convert)
                                                                                         .collect(Collectors.toList()))
                                                                            .build()))
                            .build())
                    .build();
        });
        return Optional.of(this.report);
    }

    private static AssertionResult convert(RunnerResultItem item) {
        return AssertionResult.builder()
                              .passed(item.isSuccess())
                              .text(item.getDescriber())
                              .items(Collections.singletonList(new AssertionResultItem(item.isSuccess(), item.getOutput())))
                              .build();
    }
}
