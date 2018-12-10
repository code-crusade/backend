package com.etsmtl.codecrusade.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Configuration aggregation for unsecured dev deployment. Doesn't activate any service registration components.
 */
@Configuration
@Profile("dev")
@Import({WebConfig.class, AuditingConfig.class, I18nConfig.class, AclMethodSecurityConfiguration.class, SecurityConfig.class})
public class DevConfig {
}
