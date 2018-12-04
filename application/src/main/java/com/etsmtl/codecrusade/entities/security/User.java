package com.etsmtl.codecrusade.entities.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "user_type")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "firstName")
    @Size(max = 100)
    private String firstName;

    @Column(name = "lastName")
    @Size(max = 100)
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;

    @OneToMany
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk-user_authority-user"))
    private Set<UserAuthority> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<GroupMember> memberships;
}
