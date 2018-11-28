package com.etsmtl.codecrusade.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssertionResultItem {
    private Boolean passed;

    private String message;
}
