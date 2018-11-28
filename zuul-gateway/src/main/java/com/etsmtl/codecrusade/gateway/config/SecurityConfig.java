package com.etsmtl.codecrusade.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable().httpBasic().disable()
            .requiresChannel()
            .anyRequest()
            .requiresSecure()
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
            .redirectionEndpoint()
            .baseUri("/oauth2/redirect");
    }
}