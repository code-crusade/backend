package com.etsmtl.codecrusade;

import com.etsmtl.codecrusade.configuration.*;
import com.etsmtl.codecrusade.entities.*;
import com.etsmtl.codecrusade.entities.EntryPoint;
import com.etsmtl.codecrusade.entities.Template;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.entities.security.Auditable;
import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.model.*;
import com.etsmtl.codecrusade.model.Exercise;
import com.etsmtl.codecrusade.repository.ClassGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.etsmtl.codecrusade.util.Utilities.nullable;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Configuration
@Import({SecurityConfig.class, WebConfig.class, CASConfig.class, AuditingConfig.class, I18nConfig.class})
public class ApplicationConfiguration {
    private static final String FR_CA = Locale.CANADA_FRENCH.toLanguageTag();
    private static final String EN_CA = Locale.CANADA.toLanguageTag();

    @Bean
    public ModelMapper modelMapper(ClassGroupRepository groupRepository) {
        ModelMapper modelMapper = new ModelMapper();
        // Convert Exercise entity -> Exercise Model
        modelMapper.createTypeMap(com.etsmtl.codecrusade.entities.Exercise.class, Exercise.class)
                   .setConverter(context -> {
                       com.etsmtl.codecrusade.entities.Exercise src = context.getSource();
                       // TODO : sample test cases
                       return new Exercise().description(
                               new IntlString().en(nullable(src.getDescription()).map(desc -> desc.getLocalizations()
                                                                                                  .get(EN_CA))
                                                                                 .orElse(null))
                                               .fr(nullable(src.getDescription()).map(desc -> desc.getLocalizations()
                                                                                                  .get(FR_CA))
                                                                                 .orElse(null)))
                                            .title(new IntlString().en(nullable(src.getTitle()).map(desc -> desc.getLocalizations()
                                                                                                                .get(EN_CA))
                                                                                               .orElse(null))
                                                                   .fr(nullable(src.getTitle()).map(desc -> desc.getLocalizations()
                                                                                                                .get(FR_CA))
                                                                                               .orElse(null)))
                                            .difficulty(modelMapper.map(src.getDifficulty(), Difficulties.class))
                                            .id(src.getId())
                                            .supportedLanguages(src.getSupportedLanguages())
                                            .template(modelMapper.map(src.getTemplate(),
                                                    com.etsmtl.codecrusade.model.Template.class))
                                            .sampleTestCases(src.getTestCases()
                                                                .stream()
                                                                .map(testCase -> modelMapper.map(testCase,
                                                                        TestCase.class))
                                                                .collect(toList()));
                   });

        // Convert Submission -> ExerciseSubmission
        modelMapper.createTypeMap(Submission.class, ExerciseSubmission.class)
                   .addMapping(src -> src.getExercise().getId(), ExerciseSubmission::setExerciseId)
                   .addMapping(Submission::getId, ExerciseSubmission::setId)
                   .addMapping(src -> src.getUser().getId(), ExerciseSubmission::setUserId)
                   .addMapping(Auditable::getCreationDate, ExerciseSubmission::setCreatedAt)
                   .addMapping(src -> src.getProgram().getLanguage(), ExerciseSubmission::setLanguage)
                   .addMapping(src -> src.getProgram().getCode(), ExerciseSubmission::setCode);

        // Convert ApplicationSupportedType -> SupportedType
        modelMapper.createTypeMap(ApplicationSupportedType.class, SupportedType.class).setConverter(context -> {
            ApplicationSupportedType source = context.getSource();
            if (source != null) {
                return SupportedType.fromValue(source.getValue());
            }
            return null;
        });

        // Convert Difficulty -> Difficulties
        modelMapper.createTypeMap(Difficulty.class, Difficulties.class).setConverter(context -> {
            Difficulty source = context.getSource();
            if (source != null) {
                return Difficulties.fromValue(source.getValue());
            }
            return null;
        });

        // Convert SubmissionArgument -> RunnerArguments
        modelMapper.createTypeMap(SubmissionArgument.class, RunnerArguments.class);

        // Convert RunnerArguments -> SubmissionArgument
        modelMapper.createTypeMap(RunnerArguments.class, SubmissionArgument.class);

        // Convert CodeValidationReportResults -> CodeValidationReportResults
        modelMapper.createTypeMap(CodeValidationResults.class, CodeValidationReportResults.class)
                   .addMapping(src -> src.getTest().getInputParameters(),
                           CodeValidationReportResults::setInputParameters)
                   .addMapping(src -> src.getTest().getExpectedOutput(),
                           CodeValidationReportResults::setExpectedOutput);

        // Convert Template entity -> Template model
        modelMapper.createTypeMap(Template.class, com.etsmtl.codecrusade.model.Template.class).setConverter(context -> {
            Template src = context.getSource();
            List<TemplateCode> templateCodes = nullable(src.getTemplateCode()).orElse(emptyList());

            Map<String, String> appends = templateCodes.stream()
                                                       .collect(toMap(codes -> codes.getId().getLang(),
                                                               TemplateCode::getAppendedCode));
            Map<String, String> prepends = templateCodes.stream()
                                                        .collect(toMap(codes -> codes.getId().getLang(),
                                                                TemplateCode::getPrependedCode));

            return new com.etsmtl.codecrusade.model.Template().appendedCode(appends)
                                                              .prependedCode(prepends)
                                                              .className(nullable(src.getEntryPoint()).map(
                                                                      EntryPoint::getClassName).orElse(""))
                                                              .functionName(nullable(src.getEntryPoint()).map(
                                                                      EntryPoint::getFunctionName).orElse(""))
                                                              .params(nullable(src.getEntryPoint()).map(
                                                                      entryPoint -> nullable(
                                                                              entryPoint.getFunctionParams()).map(
                                                                              params -> params.stream()
                                                                                              .map(functionParam -> modelMapper
                                                                                                      .map(functionParam,
                                                                                                              Parameter.class))
                                                                                              .collect(toList()))
                                                                                                             .orElse(emptyList()))
                                                                                                   .orElse(emptyList()))
                                                              .functionReturnType(
                                                                      modelMapper.map(src.getFunctionReturnType(),
                                                                              SupportedType.class))
                                                              .functionReturnValue(src.getFunctionReturnValue());
        });

        modelMapper.createTypeMap(Group.class, ClassGroup.class);
        modelMapper.createTypeMap(ClassGroup.class, Group.class)
                   .addMappings(mapper -> mapper.skip(Group::setStudentsIds));
        modelMapper.createTypeMap(Semester.class, Semesters.class)
                   .setConverter(context -> Semesters.fromValue(context.getSource().getValue()));
        // we have to rely on this since there is no getter generated
        modelMapper.createTypeMap(Semesters.class, Semester.class)
                   .setConverter(context -> Semester.fromValue(context.getSource().toString()));
        // Convert ApplicationTestCase -> TestCase
        modelMapper.createTypeMap(ApplicationTestCase.class, TestCase.class);
        modelMapper.createTypeMap(User.class, com.etsmtl.codecrusade.model.User.class)
                   .addMapping(User::getUsername, com.etsmtl.codecrusade.model.User::setEmail)
                   .addMapping(src -> "UNMAPPED", com.etsmtl.codecrusade.model.User::setFirstName)
                   .addMapping(src -> "UNMAPPED", com.etsmtl.codecrusade.model.User::setLastName);
        modelMapper.createTypeMap(com.etsmtl.codecrusade.model.User.class, User.class)
                   .addMapping(com.etsmtl.codecrusade.model.User::getEmail, User::setUsername)
                   .addMappings(mapper -> mapper.skip(User::setRoles));
        return modelMapper;
    }
}
