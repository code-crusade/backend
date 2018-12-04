package com.etsmtl.codecrusade.entities.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_authority")
@Data
public class UserAuthority implements GrantedAuthority {

    @EmbeddedId
    private AuthorityId id;

    @MapsId("authority")
    String authority;
}
