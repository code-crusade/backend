package com.etsmtl.codecrusade;

import com.etsmtl.codecrusade.configuration.CASConfig;
import com.etsmtl.codecrusade.configuration.SecurityConfig;
import com.etsmtl.codecrusade.configuration.SwaggerConfig;
import com.etsmtl.codecrusade.configuration.WebConfig;
import com.etsmtl.codecrusade.entities.ApplicationSupportedType;
import com.etsmtl.codecrusade.entities.EntryPoint;
import com.etsmtl.codecrusade.entities.FunctionParams;
import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.model.EntryPointFunctionParams;
import com.etsmtl.codecrusade.model.Exercise;
import com.etsmtl.codecrusade.model.ExerciseSubmission;
import com.etsmtl.codecrusade.model.SupportedType;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SwaggerConfig.class, SecurityConfig.class, WebConfig.class, CASConfig.class })
public class ApplicationConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.createTypeMap(com.etsmtl.codecrusade.entities.Exercise.class, Exercise.class)
				   .addMapping(com.etsmtl.codecrusade.entities.Exercise::getId, Exercise::setExerciseId);
		modelMapper.createTypeMap(Submission.class, ExerciseSubmission.class)
				   .addMapping(src -> src.getExercise().getId(), ExerciseSubmission::setExerciseId)
				   .addMapping(Submission::getId, ExerciseSubmission::setSubmissionId)
				   .addMapping(src -> src.getUser().getId(), ExerciseSubmission::setUserId);
		modelMapper.createTypeMap(ApplicationSupportedType.class, SupportedType.class).setConverter(context -> {
			ApplicationSupportedType source = context.getSource();
			if (source != null) {
				return SupportedType.fromValue(source.getValue());
			}
			return null;
		});
		modelMapper.createTypeMap(EntryPoint.class, com.etsmtl.codecrusade.model.EntryPoint.class);
		modelMapper.createTypeMap(FunctionParams.class, EntryPointFunctionParams.class);
		return modelMapper;
	}
}
