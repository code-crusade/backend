package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.ObjectListAttributeConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assertion_result")
@Data
@Builder
@AllArgsConstructor
public class AssertionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "passed")
    private Boolean passed;

    @Column(name = "text")
    @Size(max = 500)
    private String text;

    @Column(name = "items")
    @Convert(converter = ObjectListAttributeConverter.class)
    @Lob
    @Builder.Default
    private List<AssertionResultItem> items = new ArrayList<>();
}
