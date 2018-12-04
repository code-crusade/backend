package com.etsmtl.codecrusade.entities.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@Data
public class AuthorityId {
    @Column(name = "user_id")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Integer user_id;

    @Column(name = "authority")
    @Size(max = 50)
    @NotNull
    private String authority;
}
