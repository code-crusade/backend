package com.etsmtl.codecrusade.entities.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "user_type")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

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
    private List<UserAuthority> authorities = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<GroupMember> memberships;
}
