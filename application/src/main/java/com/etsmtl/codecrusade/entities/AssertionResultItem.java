package com.etsmtl.codecrusade.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AssertionResultItem {
    private Boolean passed;

    @Size(max = 500)
    private String message;
}
