package com.etsmtl.codecrusade.entities.security;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "group_member")
@Data
@NoArgsConstructor
public class GroupMember {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

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
