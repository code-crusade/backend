package com.etsmtl.codecrusade;

import com.etsmtl.codecrusade.configuration.CASConfig;
import com.etsmtl.codecrusade.configuration.SecurityConfig;
import com.etsmtl.codecrusade.configuration.SwaggerConfig;
import com.etsmtl.codecrusade.configuration.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SwaggerConfig.class, SecurityConfig.class, WebConfig.class, CASConfig.class })
public class ApplicationConfiguration {
}
