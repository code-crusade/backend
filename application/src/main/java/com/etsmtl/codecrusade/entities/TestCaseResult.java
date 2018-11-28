package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
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
    private String text;

    @OneToMany
    @JoinColumn(name = "test_case_result_id", foreignKey = @ForeignKey(name="fk_test_case_result_id"))
    private List<AssertionResult> items = new ArrayList<>();

}
