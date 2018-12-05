package com.etsmtl.codecrusade.entities.security;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "group_member")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GroupMember {

    @Id
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Long id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk-group_member-group"))
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk-group_member-user"))
    private User user;

    @Builder
    public GroupMember(Group group, User user) {
        this.group = group;
        this.user = user;
    }
}
