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
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TestCaseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    @Column(name = "passed")
    private Boolean passed;

    @Column(name = "text")
    @Size(max = 500)
    private String text;

    @OneToMany
    @JoinColumn(name = "test_case_result_id", foreignKey = @ForeignKey(name="fk_test_case_result_id"))
    private List<AssertionResult> items = new ArrayList<>();

}
