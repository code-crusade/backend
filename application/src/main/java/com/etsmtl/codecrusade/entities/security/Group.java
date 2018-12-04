package com.etsmtl.codecrusade.entities.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_group_authorities_group"))
    private Set<GroupAuthority> groupAuthorities = new HashSet<>();

    @ManyToMany(mappedBy = "group")
    private Set<GroupMember> members;
}
