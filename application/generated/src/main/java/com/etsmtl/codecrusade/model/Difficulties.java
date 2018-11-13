package com.etsmtl.codecrusade.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Exercise difficulty range
 */
public enum Difficulties {
  
  EASY("EASY"),
  
  MEDIUM("MEDIUM"),
  
  HARD("HARD");

  private String value;

  Difficulties(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Difficulties fromValue(String text) {
    for (Difficulties b : Difficulties.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

