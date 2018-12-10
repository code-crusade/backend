package com.etsmtl.codecrusade.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class TokenHelper {
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                   // DO NOT DEPLOY THIS APPLICATION DIRECTLY WITH NO GATEWAY SETUP
                   // .setSigningKey(this.SECRET)
                   .parseClaimsJws(token)
                   .getBody();
    }
}
