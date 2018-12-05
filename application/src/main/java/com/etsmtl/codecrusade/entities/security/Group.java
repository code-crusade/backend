package com.etsmtl.codecrusade.entities.security;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Long id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_group_authorities_group"))
    private List<GroupAuthority> groupAuthorities = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<GroupMember> members = new ArrayList<>();
}
