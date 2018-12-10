package com.etsmtl.codecrusade.configuration.profiles;

import com.etsmtl.codecrusade.configuration.*;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Configuration aggregation for secured prod deployment.
 */
@Configuration
@Profile({"prod","!dev"})
@Import({WebConfig.class, AuditingConfig.class, I18nConfig.class, AclMethodSecurityConfiguration.class, SecurityConfig.class})
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ProdConfig {
}
