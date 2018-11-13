package com.etsmtl.codecrusade.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Semester values.
 */
public enum Semesters {
  
  FALL("FALL"),
  
  WINTER("WINTER"),
  
  SUMMER("SUMMER");

  private String value;

  Semesters(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Semesters fromValue(String text) {
    for (Semesters b : Semesters.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

