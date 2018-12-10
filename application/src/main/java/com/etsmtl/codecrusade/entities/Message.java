package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Message {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Integer key;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    @ElementCollection
    @MapKeyColumn(name = "locale")
    @Column(name = "value")
    @Size(max = 5000)
    @CollectionTable(name = "localizations",
                     joinColumns = @JoinColumn(name = "message_id", foreignKey = @ForeignKey(name = "fk_message_id")))
    private Map<String, String> localizations = new HashMap<>();

    @Builder
    public Message(@Size(max = 5000) Map<String, String> localizations) {
        this.localizations = localizations;
    }
}
