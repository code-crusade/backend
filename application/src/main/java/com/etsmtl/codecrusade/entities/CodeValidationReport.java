package com.etsmtl.codecrusade.entities;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "code_validation_reports")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CodeValidationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "exercise_id")
    private Integer exerciseId;

    @Column(name = "exit_code")
    private Integer exitCode;

    @Column(name = "message")
    @Size(max = 500)
    private String message;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "code_validation_report_id", foreignKey = @ForeignKey(name = "fk_code_validation_report_id"))
    private List<TestCaseResult> result;

    @Column(name = "stderr")
    @Size(max = 5000)
    private String stderr;

    @Column(name = "stdout")
    @Size(max = 5000)
    private String stdout;

    @Column(name = "time_out")
    private boolean timedOut;

    @Column(name = "token")
    @Size(max = 5000)
    private String token;

    @Column(name = "execution_time")
    private Integer executionTime;
}
