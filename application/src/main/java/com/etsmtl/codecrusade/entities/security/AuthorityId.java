package com.etsmtl.codecrusade.entities.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class AuthorityId implements Serializable {
    @Column(name = "user_id")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Integer userId;

    @Column(name = "authority")
    @Size(max = 50)
    @NotNull
    private String authority;
}
