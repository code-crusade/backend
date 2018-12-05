package com.etsmtl.codecrusade.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssertionResultItem {
    private Boolean passed;

    @Size(max = 500)
    private String message;
}
