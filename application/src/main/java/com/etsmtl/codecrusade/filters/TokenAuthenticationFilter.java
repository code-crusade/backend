package com.etsmtl.codecrusade.filters;

import com.etsmtl.codecrusade.service.security.AnonAuthentication;
import com.etsmtl.codecrusade.service.security.TokenBasedAuthentication;
import com.etsmtl.codecrusade.service.security.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that extracts use principal info fron the request jwt token.
 * <p>
 * Adapted from https://www.linkedin.com/pulse/json-web-token-jwt-spring-security-real-world-example-boris-trivic/
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.header}")
    private String AUTH_HEADER;

    private TokenHelper tokenHelper;

    private UserDetailsService userDetailsService;

    @Autowired
    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String error = "";
        String authToken = getToken(request);
        if (authToken != null) {

            // Get username from token
            String username = tokenHelper.getUsernameFromToken(authToken);
            if (username != null) {

                // Get user
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Create authentication
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                error = "Username from token can't be found in DB.";
            }
        } else {
            error = "Authentication failed - no Bearer token provided.";
        }
        if (!error.equals("")) {
            System.out.println(error);
            SecurityContextHolder.getContext().setAuthentication(new AnonAuthentication());//prevent show login form...
        }
        chain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {

        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
