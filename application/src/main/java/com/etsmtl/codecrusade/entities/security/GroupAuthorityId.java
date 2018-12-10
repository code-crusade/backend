package com.etsmtl.codecrusade.entities.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class GroupAuthorityId implements Serializable {
    @Column(name = "group_id")
    @Setter(AccessLevel.NONE)
    private Long groupId;

    @Column(name="authority")
    @Size(max=50)
    private String authority;
}
