package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_case_result")
@Data
@Builder
@AllArgsConstructor
public class TestCaseResult {

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

    @OneToMany
    @JoinColumn(name = "test_case_result_id", foreignKey = @ForeignKey(name="fk_test_case_result_id"))
    private List<AssertionResult> items = new ArrayList<>();

}
