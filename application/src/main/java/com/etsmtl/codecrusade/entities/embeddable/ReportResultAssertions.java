package com.etsmtl.codecrusade.entities.embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResultAssertions {
    private Integer passed;

    private Integer failed;
}
