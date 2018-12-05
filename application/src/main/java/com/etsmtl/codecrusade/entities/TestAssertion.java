package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.ObjectAttributeConverter;
import com.etsmtl.codecrusade.entities.converters.ObjectListAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assertions")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TestAssertion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Integer id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    @Column(name = "inputArguments")
    @Convert(converter = ObjectListAttributeConverter.class)
    private List<TestArgument> inputArguments = new ArrayList<>();

    @Column(name = "expectedOutput")
    @Convert(converter = ObjectAttributeConverter.class)
    private TestArgument expectedOutput;
}
