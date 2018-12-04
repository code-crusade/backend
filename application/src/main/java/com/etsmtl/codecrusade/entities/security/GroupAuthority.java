package com.etsmtl.codecrusade.entities.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "group_authority")
@Data
@NoArgsConstructor
public class GroupAuthority implements GrantedAuthority {
    @EmbeddedId
    private GroupAuthorityId groupAuthorityId;

    @MapsId("authority")
    private String authority;
}
