package com.etsmtl.codecrusade.entities.embeddable;

import com.etsmtl.codecrusade.entities.TestCaseResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Data
@Builder
@AllArgsConstructor
public class ReportResult {

    @Embedded
    private ReportResultAssertions assertions;

    @Column(name="completed")
    private Boolean completed;

    @OneToMany
    @JoinColumn(name = "report_id", foreignKey = @ForeignKey(name = "fk_report_id"))
    private List<TestCaseResult> output;
}
