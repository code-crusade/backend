package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.ReportResult;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reports")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "fk_exercise_id"))
    private Exercise exercise;

    @OneToOne
    @JoinColumn(name = "submission_id", foreignKey = @ForeignKey(name = "fk_submission_id"))
    private Submission submission;

    @Column(name = "exitCode")
    private Integer exitCode;

    @Column(name = "message")
    private String message;

    @Embedded
    private ReportResult result;

    @Column(name = "stderr")
    private String stderr;

    @Column(name = "stdout")
    private String stdout;

    @Column(name = "timedOut")
    private Boolean timedOut;

    @Column(name = "token")
    private String token;

    @Column(name = "executionTime")
    private Integer executionTime;
}
