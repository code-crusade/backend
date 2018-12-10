package com.etsmtl.codecrusade.gateway.config;

import com.etsmtl.codecrusade.gateway.filters.OpenIdConnectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@Import(GoogleOauthConfig.class)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Bean
    public OpenIdConnectFilter openIdConnectFilter() {
        OpenIdConnectFilter filter = new OpenIdConnectFilter("/google-login");
        filter.setRestTemplate(restTemplate);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable().httpBasic().disable()
            .requiresChannel()
            .anyRequest()
            .requiresSecure()
            .and()
            .addFilterAfter(new OAuth2ClientContextFilter(),
                    AbstractPreAuthenticatedProcessingFilter.class)
            .addFilterAfter(openIdConnectFilter(),
                    OAuth2ClientContextFilter.class)
            .httpBasic()
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/google-login"))
            .and()
            .authorizeRequests()
            .antMatchers("/app/**")
            .permitAll()
            .and()
            .authorizeRequests()
            .anyRequest().authenticated();
    }
}