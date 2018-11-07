package com.etsmtl.codecrusade;

import com.etsmtl.codecrusade.configuration.*;
import com.etsmtl.codecrusade.entities.*;
import com.etsmtl.codecrusade.entities.EntryPoint;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.entities.security.Auditable;
import com.etsmtl.codecrusade.model.*;
import com.etsmtl.codecrusade.model.Exercise;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SecurityConfig.class, WebConfig.class, CASConfig.class, AuditingConfig.class })
public class ApplicationConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.createTypeMap(com.etsmtl.codecrusade.entities.Exercise.class, Exercise.class)
				   .addMapping(com.etsmtl.codecrusade.entities.Exercise::getId, Exercise::setExerciseId);
		modelMapper.createTypeMap(Submission.class, ExerciseSubmission.class)
				   .addMapping(src -> src.getExercise().getId(), ExerciseSubmission::setExerciseId)
				   .addMapping(Submission::getId, ExerciseSubmission::setSubmissionId)
				   .addMapping(src -> src.getUser().getId(), ExerciseSubmission::setUserId)
				   .addMapping(Auditable::getCreationDate, ExerciseSubmission::setCreatedAt);
		modelMapper.createTypeMap(ApplicationSupportedType.class, SupportedType.class).setConverter(context -> {
			ApplicationSupportedType source = context.getSource();
			if (source != null) {
				return SupportedType.fromValue(source.getValue());
			}
			return null;
		});
		modelMapper.createTypeMap(EntryPoint.class, com.etsmtl.codecrusade.model.EntryPoint.class);
		modelMapper.createTypeMap(FunctionParams.class, EntryPointFunctionParams.class);
		modelMapper.createTypeMap(SubmissionArgument.class, RunnerArguments.class);
		modelMapper.createTypeMap(RunnerArguments.class, SubmissionArgument.class);
		modelMapper.createTypeMap(CodeValidationResults.class, CodeValidationReportResults.class)
				   .addMapping(src -> src.getTest().getInputParameters(),
							   CodeValidationReportResults::setInputParameters)
				   .addMapping(src -> src.getTest().getExpectedOutput(),
							   CodeValidationReportResults::setExpectedOutput);

		return modelMapper;
	}
}
