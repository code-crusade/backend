package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.SupportedType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A test input and its type
 */
@ApiModel(description = "A test input and its type")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-09T14:55:12.473779600-05:00[America/New_York]")

public class TestInput   {
  @JsonProperty("inputType")
  private SupportedType inputType = null;

  @JsonProperty("inputValue")
  private Object inputValue = null;

  public TestInput inputType(SupportedType inputType) {
    this.inputType = inputType;
    return this;
  }

  /**
   * Get inputType
   * @return inputType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public SupportedType getInputType() {
    return inputType;
  }

  public void setInputType(SupportedType inputType) {
    this.inputType = inputType;
  }

  public TestInput inputValue(Object inputValue) {
    this.inputValue = inputValue;
    return this;
  }

  /**
   * Type encompassing any value type
   * @return inputValue
  **/
  @ApiModelProperty(value = "Type encompassing any value type")


  public Object getInputValue() {
    return inputValue;
  }

  public void setInputValue(Object inputValue) {
    this.inputValue = inputValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestInput testInput = (TestInput) o;
    return Objects.equals(this.inputType, testInput.inputType) &&
        Objects.equals(this.inputValue, testInput.inputValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputType, inputValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TestInput {\n");
    
    sb.append("    inputType: ").append(toIndentedString(inputType)).append("\n");
    sb.append("    inputValue: ").append(toIndentedString(inputValue)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

