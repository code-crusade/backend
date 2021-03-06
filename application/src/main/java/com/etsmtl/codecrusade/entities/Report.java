package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.ReportResult;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reports")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "fk_exercise_id"))
    private Exercise exercise;

    @OneToOne
    @JoinColumn(name = "submission_id", foreignKey = @ForeignKey(name = "fk_submission_id"))
    private Submission submission;

    @Column(name = "exitCode")
    private Integer exitCode;

    @Column(name = "message")
    @Size(max = 500)
    private String message;

    @Embedded
    private ReportResult result;

    @Column(name = "stderr")
    @Size(max = 5000)
    private String stderr;

    @Column(name = "stdout")
    @Size(max = 5000)
    private String stdout;

    @Column(name = "timedOut")
    private Boolean timedOut;

    @Column(name = "token")
    @Size(max = 5000)
    private String token;

    @Column(name = "executionTime")
    private Integer executionTime;
}
