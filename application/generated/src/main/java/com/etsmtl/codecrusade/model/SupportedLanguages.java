package com.etsmtl.codecrusade.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Languages supported by the api
 */
public enum SupportedLanguages {
  
  CPP("cpp"),
  
  JAVA("java"),
  
  JAVASCRIPT("javascript"),
  
  PYTHON("python");

  private String value;

  SupportedLanguages(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SupportedLanguages fromValue(String text) {
    for (SupportedLanguages b : SupportedLanguages.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

