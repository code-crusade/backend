package com.etsmtl.codecrusade.entities.embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
public class ReportResultAssertions {
    private Integer passed;

    private Integer failed;
}
