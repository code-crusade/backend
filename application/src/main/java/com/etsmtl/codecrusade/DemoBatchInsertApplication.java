package com.etsmtl.codecrusade;

//@SpringBootApplication
//@EnableBatchProcessing
public class DemoBatchInsertApplication {
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Bean
//    public FlatFileItemReader<ClassGroup> reader() {
//        return new FlatFileItemReaderBuilder<ClassGroup>()
//                .name("groupItemReader")
//                .resource(new ClassPathResource("demo-groups.csv"))
//                .delimited()
//                .names(new String[]{"groupNumber", "course", "semester", "year", "archived"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                    setTargetType(ClassGroup.class);
//                }})
//                .build();
//    }
//
//    @Bean
//    public FieldSetMapper<ClassGroup> groupFieldSetMapper() {
//        return (fieldSet -> {
//            return ClassGroup.builder().groupNumber()
//        });
//    }
}
