package com.etsmtl.codecrusade.configuration.profiles;

import com.etsmtl.codecrusade.configuration.AclMethodSecurityConfiguration;
import com.etsmtl.codecrusade.configuration.AuditingConfig;
import com.etsmtl.codecrusade.configuration.I18nConfig;
import com.etsmtl.codecrusade.configuration.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration aggregation for unsecured dev deployment. Doesn't activate any service registration components.
 */
@Configuration
@Profile("dev")
@Import({WebConfig.class, AuditingConfig.class, I18nConfig.class, AclMethodSecurityConfiguration.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DevConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
            .antMatchers("/**")
            .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password("password")
            .roles("USER")
            .and()
            .withUser("admin")
            .password("password")
            .roles("USER", "ADMIN");
    }
}
