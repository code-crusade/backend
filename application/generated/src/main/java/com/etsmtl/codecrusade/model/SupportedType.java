package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets SupportedType
 */
public enum SupportedType {
  
  BOOLEAN("boolean"),
  
  INT("int"),
  
  FLOAT("float"),
  
  STRING("string"),
  
  BOOLEAN_("boolean[]"),
  
  INT_("int[]"),
  
  FLOAT_("float[]"),
  
  STRING_("string[]");

  private String value;

  SupportedType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SupportedType fromValue(String text) {
    for (SupportedType b : SupportedType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

