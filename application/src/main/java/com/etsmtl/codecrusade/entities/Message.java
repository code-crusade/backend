package com.etsmtl.codecrusade.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    String key;

    @ElementCollection
    @MapKeyColumn(name = "locale")
    @Column(name = "value")
    @CollectionTable(name = "localizations", joinColumns = @JoinColumn(name = "message_id", foreignKey = @ForeignKey(name = "fk_message_id")))
    private Map<String, String> localizations = new HashMap<>();
}