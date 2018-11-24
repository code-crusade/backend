package com.etsmtl.codecrusade.entities.security;

import com.etsmtl.codecrusade.entities.converters.StringSetAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false,
            unique = true)
    private String username;

    private String password;

    @Column(name = "roles")
    @Convert(converter = StringSetAttributeConverter.class)
    private Set<String> roles = new HashSet<>();
}
