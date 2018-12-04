package com.etsmtl.codecrusade.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "functionParams")
@Getter
@Setter
/**
 * TODO : make those embeddable
 */
public class FunctionParams {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private ApplicationSupportedType type;
}
