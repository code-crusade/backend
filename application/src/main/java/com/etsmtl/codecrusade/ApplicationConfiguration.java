package com.etsmtl.codecrusade;

import com.etsmtl.codecrusade.entities.*;
import com.etsmtl.codecrusade.entities.embeddable.ReportResult;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.entities.embeddable.TemplateCodeId;
import com.etsmtl.codecrusade.entities.security.Auditable;
import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.model.*;
import com.etsmtl.codecrusade.repository.ClassGroupRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

import static com.etsmtl.codecrusade.util.Utilities.nullable;

@Configuration
public class ApplicationConfiguration {
    private static final String FR_CA = Locale.CANADA_FRENCH.toLanguageTag();
    private static final String EN_CA = Locale.CANADA.toLanguageTag();

    @Autowired
    private ModelMapper mapper;

    private Converter<Message, IntlStringModel> messageToIntlStringModelConverter = (MappingContext<Message, IntlStringModel> context) -> {
        Message src = context.getSource();
        return new IntlStringModel().en(nullable(src).map(desc -> desc.getLocalizations()
                                                                      .get(EN_CA))
                                                     .orElse(null))
                                    .fr(nullable(src).map(desc -> desc.getLocalizations()
                                                                      .get(FR_CA))
                                                     .orElse(null));
    };

    private Converter<IntlStringModel, Message> IntlStringModelToMessageConverter = (MappingContext<IntlStringModel, Message> context) -> Message
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

        modelMapper.createTypeMap(Exercise.class, ExerciseModel.class)
                   .addMappings(mapping -> mapping.using(messageToIntlStringModelConverter)
                                                  .map(Exercise::getDescription, ExerciseModel::setDescription))
                   .addMappings(mapping -> mapping.using(messageToIntlStringModelConverter)
                                                  .map(Exercise::getTitle, ExerciseModel::setTitle))
                   .addMapping(Exercise::getTestCases, ExerciseModel::setSampleTestCases)
                   .addMappings(mapping -> mapping.skip(ExerciseModel::setFixtures))
                   .addMapping(Exercise::getTemplate, ExerciseModel::setTemplate);
        modelMapper.createTypeMap(ExerciseModel.class, Exercise.class)
                   .addMappings(mapping -> mapping.using(IntlStringModelToMessageConverter)
                                                  .map(ExerciseModel::getTitle, Exercise::setTitle))
                   .addMappings(mapping -> mapping.using(IntlStringModelToMessageConverter)
                                                  .map(ExerciseModel::getDescription, Exercise::setDescription))
                   .addMapping(ExerciseModel::getSampleTestCases, Exercise::setTestCases)
                   .addMapping(ExerciseModel::getFixtures, Exercise::setFixtures)
                   .addMapping(ExerciseModel::getTemplate, Exercise::setTemplate);

        // Convert Submission -> ExerciseSubmissionModel
        modelMapper.createTypeMap(Submission.class, ExerciseSubmissionModel.class)
                   .addMapping(src -> src.getExercise().getId(), ExerciseSubmissionModel::setExerciseId)
                   .addMapping(Submission::getId, ExerciseSubmissionModel::setId)
                   .addMapping(src -> src.getUser().getId(), ExerciseSubmissionModel::setUserId)
                   .addMapping(Auditable::getCreationDate, ExerciseSubmissionModel::setCreatedAt)
                   .addMapping(Submission::getLanguage, ExerciseSubmissionModel::setLanguage)
                   .addMapping(Submission::getCode, ExerciseSubmissionModel::setCode);

        // Convert ApplicationSupportedType -> SupportedType
        modelMapper.createTypeMap(ApplicationSupportedType.class, SupportedTypeModel.class)
                   .setConverter(context -> {
                       ApplicationSupportedType source = context.getSource();
                       if (source != null) {
                           return SupportedTypeModel.fromValue(source.getValue());
                       }
                       return null;
                   });

        // Convert Difficulty -> DifficultiesModel
        modelMapper.createTypeMap(Difficulty.class, DifficultiesModel.class).setConverter(context -> {
            Difficulty source = context.getSource();
            if (source != null) {
                return DifficultiesModel.fromValue(source.getValue());
            }
            return null;
        });

        // Convert SubmissionArgument -> RunnerArguments
        modelMapper.createTypeMap(SubmissionArgument.class, RunnerArgumentsModel.class);

        // Convert RunnerArguments -> SubmissionArgument
        modelMapper.createTypeMap(RunnerArgumentsModel.class, SubmissionArgument.class);

        // Convert CodeValidationReportResults -> CodeValidationReportResults
        modelMapper.createTypeMap(ReportResult.class, CodeValidationReportResultModel.class);

        modelMapper.createTypeMap(Template.class, com.etsmtl.codecrusade.model.TemplateModel.class)
                   .addMappings(mapping -> mapping.using((Converter<List<TemplateCode>, Map<String, String>>) context -> modelMapper
                           .map(context.getSource()
                                       .stream()
                                       .collect(Collectors.toMap(codes -> codes.getId()
                                                                               .getLang(), TemplateCode::getPrependedCode)), new TypeToken<Map<String, String>>() {
                           }.getType()))
                                                  .map(Template::getTemplateCode, TemplateModel::setPrependedCode))
                   .addMappings(mapping -> mapping.using((Converter<List<TemplateCode>, Map<String, String>>) context -> modelMapper
                           .map(context.getSource()
                                       .stream()
                                       .collect(Collectors.toMap(codes -> codes.getId()
                                                                               .getLang(), TemplateCode::getAppendedCode)), new TypeToken<Map<String, String>>() {
                           }.getType()))
                                                  .map(Template::getTemplateCode, TemplateModel::setAppendedCode));
        modelMapper.createTypeMap(TemplateModel.class, Template.class).setConverter(context -> {
            {
                TemplateModel source = context.getSource();
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
        modelMapper.createTypeMap(GroupModel.class, ClassGroup.class);
        modelMapper.createTypeMap(ClassGroup.class, GroupModel.class);
        modelMapper.createTypeMap(Semester.class, SemestersModel.class)
                   .setConverter(context -> SemestersModel.fromValue(context.getSource().getValue()));
        // we have to rely on this since there is no getter generated
        modelMapper.createTypeMap(SemestersModel.class, Semester.class)
                   .setConverter(context -> Semester.fromValue(context.getSource().toString()));
        // Convert ApplicationTestCase -> TestCase
        modelMapper.createTypeMap(ApplicationTestCase.class, TestCaseModel.class);
        modelMapper.createTypeMap(User.class, UserModel.class);
        modelMapper.createTypeMap(UserModel.class, User.class)
                   .addMappings(mapper -> mapper.skip(User::setAuthorities))
                   .addMappings(mapper -> mapper.skip(User::setMemberships));
        modelMapper.createTypeMap(Report.class, CodeValidationReportModel.class);
        modelMapper.createTypeMap(Student.class, StudentModel.class)
                   .addMapping(Student::getSubmissions, StudentModel::setExcersises)
                   .addMappings(mapping -> mapping.skip(StudentModel::setAccessCode))
                   .addMappings(mapping -> mapping.skip(StudentModel::setTotalExercisesDone))
                   .addMappings(mapping -> mapping.skip(StudentModel::setTotalExercisesSuccessed));
        return modelMapper;
    }

}
