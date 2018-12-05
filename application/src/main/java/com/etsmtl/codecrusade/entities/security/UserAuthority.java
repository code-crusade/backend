package com.etsmtl.codecrusade.entities.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_authority")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserAuthority implements GrantedAuthority {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private AuthorityId id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    /**
     * Getter for the string authority this represents. Shortcuts to groupAuthorityId.getAuthority().
     *
     * @return a String authority
     */
    public String getAuthority() {
        if (id != null) {
            return id.getAuthority();
        } else {
            return null;
        }
    }
}
