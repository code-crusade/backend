package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.ObjectAttributeConverter;
import com.etsmtl.codecrusade.entities.converters.ObjectListAttributeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assertions")
@Data
@NoArgsConstructor
public class TestAssertion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "inputArguments")
    @Convert(converter = ObjectListAttributeConverter.class)
    private List<TestArgument> inputArguments = new ArrayList<>();

    @Column(name = "expectedOutput")
    @Convert(converter = ObjectAttributeConverter.class)
    private TestArgument expectedOutput;
}
