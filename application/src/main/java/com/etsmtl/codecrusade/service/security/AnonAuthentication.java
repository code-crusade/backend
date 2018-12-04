package com.etsmtl.codecrusade.service.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AnonAuthentication extends AbstractAuthenticationToken {

    public AnonAuthentication(){
        // initialized with no authorities
        super(null);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
