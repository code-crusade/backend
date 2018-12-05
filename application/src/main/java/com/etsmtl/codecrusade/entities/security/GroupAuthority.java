package com.etsmtl.codecrusade.entities.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "group_authority")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GroupAuthority implements GrantedAuthority {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private GroupAuthorityId groupAuthorityId;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;


    /**
     * Getter for the string authority this represents. Shortcuts to groupAuthorityId.getAuthority().
     *
     * @return a String authority
     */
    @Override
    public String getAuthority() {
        if (groupAuthorityId != null) {
            return groupAuthorityId.getAuthority();
        } else {
            return null;
        }
    }
}
