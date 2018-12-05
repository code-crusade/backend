package com.etsmtl.codecrusade;

import com.etsmtl.codecrusade.configuration.DevConfig;
import com.etsmtl.codecrusade.configuration.ProdConfig;
import com.etsmtl.codecrusade.entities.*;
import com.etsmtl.codecrusade.entities.Template;
import com.etsmtl.codecrusade.entities.embeddable.ReportResult;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.entities.embeddable.TemplateCodeId;
import com.etsmtl.codecrusade.entities.security.Auditable;
import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.model.*;
import com.etsmtl.codecrusade.model.Exercise;
import com.etsmtl.codecrusade.repository.ClassGroupRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.*;
import java.util.stream.Collectors;

import static com.etsmtl.codecrusade.util.Utilities.nullable;

@Configuration
@Import({ProdConfig.class, DevConfig.class})
public class ApplicationConfiguration {
    private static final String FR_CA = Locale.CANADA_FRENCH.toLanguageTag();
    private static final String EN_CA = Locale.CANADA.toLanguageTag();

    @Autowired
    private ModelMapper mapper;

    private Converter<Message, IntlString> messageToIntlStringConverter = (MappingContext<Message, IntlString> context) -> {
        Message src = context.getSource();
        return new IntlString().en(nullable(src).map(desc -> desc.getLocalizations()
                                                                 .get(EN_CA))
                                                .orElse(null))
                               .fr(nullable(src).map(desc -> desc.getLocalizations()
                                                                 .get(FR_CA))
                                                .orElse(null));
    };

    private Converter<IntlString, Message> intlStringToMessageConverter = (MappingContext<IntlString, Message> context) -> Message
            .builder()
            .localizations(mapper.map(context.getSource(), new TypeToken<Map<String, String>>() {
            }.getType()))
            .build();

    @Bean
    public ModelMapper modelMapper(ClassGroupRepository groupRepository) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        // TODO: hide exercise fixtures
        // Convert Exercise entity -> Exercise Model

        modelMapper.createTypeMap(com.etsmtl.codecrusade.entities.Exercise.class, Exercise.class)
                   .addMappings(mapping -> mapping.using(messageToIntlStringConverter)
                                                  .map(com.etsmtl.codecrusade.entities.Exercise::getDescription, Exercise::setDescription))
                   .addMappings(mapping -> mapping.using(messageToIntlStringConverter)
                                                  .map(com.etsmtl.codecrusade.entities.Exercise::getTitle, Exercise::setTitle))
                   .addMapping(com.etsmtl.codecrusade.entities.Exercise::getTestCases, Exercise::setSampleTestCases)
                   .addMappings(mapping -> mapping.skip(Exercise::setFixtures))
                   .addMapping(com.etsmtl.codecrusade.entities.Exercise::getTemplate, Exercise::setTemplate);
        modelMapper.createTypeMap(Exercise.class, com.etsmtl.codecrusade.entities.Exercise.class)
                   .addMappings(mapping -> mapping.using(intlStringToMessageConverter)
                                                  .map(Exercise::getTitle, com.etsmtl.codecrusade.entities.Exercise::setTitle))
                   .addMappings(mapping -> mapping.using(intlStringToMessageConverter)
                                                  .map(Exercise::getDescription, com.etsmtl.codecrusade.entities.Exercise::setDescription))
                   .addMapping(Exercise::getSampleTestCases, com.etsmtl.codecrusade.entities.Exercise::setTestCases)
                   .addMapping(Exercise::getFixtures, com.etsmtl.codecrusade.entities.Exercise::setFixtures)
                   .addMapping(Exercise::getTemplate, com.etsmtl.codecrusade.entities.Exercise::setTemplate);
        // Convert Submission -> ExerciseSubmission
        modelMapper.createTypeMap(Submission.class, ExerciseSubmission.class)
                   .addMapping(src -> src.getExercise().getId(), ExerciseSubmission::setExerciseId)
                   .addMapping(Submission::getId, ExerciseSubmission::setId)
                   .addMapping(src -> src.getUser().getId(), ExerciseSubmission::setUserId)
                   .addMapping(Auditable::getCreationDate, ExerciseSubmission::setCreatedAt)
                   .addMapping(Submission::getLanguage, ExerciseSubmission::setLanguage)
                   .addMapping(Submission::getCode, ExerciseSubmission::setCode);

        // Convert ApplicationSupportedType -> SupportedType
        modelMapper.createTypeMap(ApplicationSupportedType.class, SupportedType.class)
                   .setConverter(context -> {
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
        modelMapper.createTypeMap(ReportResult.class, CodeValidationReportResult.class);

        modelMapper.createTypeMap(Template.class, com.etsmtl.codecrusade.model.Template.class)
                   .addMappings(mapping -> mapping.using((Converter<List<TemplateCode>, Map<String, String>>) context -> modelMapper
                           .map(context.getSource()
                                       .stream()
                                       .collect(Collectors.toMap(codes -> codes.getId()
                                                                               .getLang(), TemplateCode::getPrependedCode)), new TypeToken<Map<String, String>>() {
                           }.getType()))
                                                  .map(Template::getTemplateCode, com.etsmtl.codecrusade.model.Template::setPrependedCode))
                   .addMappings(mapping -> mapping.using((Converter<List<TemplateCode>, Map<String, String>>) context -> modelMapper
                           .map(context.getSource()
                                       .stream()
                                       .collect(Collectors.toMap(codes -> codes.getId()
                                                                               .getLang(), TemplateCode::getAppendedCode)), new TypeToken<Map<String, String>>() {
                           }.getType()))
                                                  .map(Template::getTemplateCode, com.etsmtl.codecrusade.model.Template::setAppendedCode));
        modelMapper.createTypeMap(com.etsmtl.codecrusade.model.Template.class, Template.class).setConverter(context -> {
            {
                com.etsmtl.codecrusade.model.Template source = context.getSource();
                Map<String, String> prependedCode = source.getPrependedCode();
                Map<String, String> appendedCode = source.getAppendedCode();
                Set<String> keys = new HashSet<>();
                keys.addAll(prependedCode.keySet());
                keys.addAll(appendedCode.keySet());
                List<TemplateCode> templateCodes = keys.stream()
                                                       .map(key -> TemplateCode.builder()
                                                                               .id(TemplateCodeId.builder()
                                                                                                 .lang(key)
                                                                                                 .build())
                                                                               .appendedCode(appendedCode.get(key))
                                                                               .prependedCode(prependedCode
                                                                                       .get(key))
                                                                               .build())
                                                       .collect(Collectors.toList());
                return Template.builder()
                               .entryPoint(EntryPoint.builder()
                                                     .functionName(source.getFunctionName())
                                                     .functionParams(modelMapper.map(source.getParams(), new TypeToken<List<FunctionParams>>() {
                                                     }.getType()))
                                                     .returnType(modelMapper.map(source.getFunctionReturnType(), ApplicationSupportedType.class))
                                                     .build())
                               .templateCode(templateCodes)
                               .functionReturnType(modelMapper.map(source.getFunctionReturnType(), ApplicationSupportedType.class))
                               .build();
            }
        });
        // Todo : this could be simplified
        // Convert Template entity -> Template model
//        modelMapper.createTypeMap(Template.class, com.etsmtl.codecrusade.model.Template.class)
//                   .setConverter(context -> {
//                       Template src = context.getSource();
//                       List<TemplateCode> templateCodes = nullable(src.getTemplateCode()).orElse(emptyList());
//
//                       Map<String, String> appends = templateCodes.stream()
//                                                                  .collect(toMap(codes -> codes.getId()
//                                                                                               .getLang(),
//                                                                          TemplateCode::getAppendedCode));
//                       Map<String, String> prepends = templateCodes.stream()
//                                                                   .collect(toMap(codes -> codes.getId()
//                                                                                                .getLang(),
//                                                                           TemplateCode::getPrependedCode));
//
//                       return new com.etsmtl.codecrusade.model.Template().appendedCode(appends)
//                                                                         .prependedCode(prepends)
//                                                                         .functionName(nullable(src.getEntryPoint())
//                                                                                 .map(
//                                                                                         EntryPoint::getFunctionName)
//                                                                                 .orElse(""))
//                                                                         .params(nullable(src.getEntryPoint())
//                                                                                 .map(
//                                                                                         entryPoint -> nullable(
//                                                                                                 entryPoint
//                                                                                                         .getFunctionParams())
//                                                                                                 .map(
//                                                                                                         params -> params
//                                                                                                                 .stream()
//                                                                                                                 .map(functionParam -> modelMapper
//                                                                                                                         .map(functionParam,
//                                                                                                                                 Parameter.class))
//                                                                                                                 .collect(toList()))
//                                                                                                 .orElse(emptyList()))
//                                                                                 .orElse(emptyList()))
//                                                                         .functionReturnType(
//                                                                                 modelMapper.map(src.getFunctionReturnType(),
//                                                                                         SupportedType.class));
//                   });
        modelMapper.createTypeMap(Group.class, ClassGroup.class);
        modelMapper.createTypeMap(ClassGroup.class, Group.class);
        modelMapper.createTypeMap(Semester.class, Semesters.class)
                   .setConverter(context -> Semesters.fromValue(context.getSource().getValue()));
        // we have to rely on this since there is no getter generated
        modelMapper.createTypeMap(Semesters.class, Semester.class)
                   .setConverter(context -> Semester.fromValue(context.getSource().toString()));
        // Convert ApplicationTestCase -> TestCase
        modelMapper.createTypeMap(ApplicationTestCase.class, TestCase.class);
        modelMapper.createTypeMap(User.class, com.etsmtl.codecrusade.model.User.class);
        modelMapper.createTypeMap(com.etsmtl.codecrusade.model.User.class, User.class)
                   .addMappings(mapper -> mapper.skip(User::setAuthorities))
                   .addMappings(mapper -> mapper.skip(User::setMemberships));
        modelMapper.createTypeMap(Report.class, CodeValidationReport.class);
        return modelMapper;
    }

}
