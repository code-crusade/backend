package com.etsmtl.codecrusade.configuration;

import com.etsmtl.codecrusade.runner.Runner;
import com.etsmtl.codecrusade.runner.codewars.CodewarsCliRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunnerConfig {
    @Bean
    public Runner runner(){
        return new CodewarsCliRunner();
    }
}
