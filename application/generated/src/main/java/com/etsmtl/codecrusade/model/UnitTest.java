package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.TestInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A simple input-ouput unit test
 */
@ApiModel(description = "A simple input-ouput unit test")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-09T14:55:12.473779600-05:00[America/New_York]")

public class UnitTest   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("input")
  @Valid
  private List<TestInput> input = null;

  @JsonProperty("outputValue")
  private Object outputValue = null;

  public UnitTest id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(readOnly = true, value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public UnitTest input(List<TestInput> input) {
    this.input = input;
    return this;
  }

  public UnitTest addInputItem(TestInput inputItem) {
    if (this.input == null) {
      this.input = new ArrayList<>();
    }
    this.input.add(inputItem);
    return this;
  }

  /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<TestInput> getInput() {
    return input;
  }

  public void setInput(List<TestInput> input) {
    this.input = input;
  }

  public UnitTest outputValue(Object outputValue) {
    this.outputValue = outputValue;
    return this;
  }

  /**
   * Type encompassing any value type
   * @return outputValue
  **/
  @ApiModelProperty(value = "Type encompassing any value type")


  public Object getOutputValue() {
    return outputValue;
  }

  public void setOutputValue(Object outputValue) {
    this.outputValue = outputValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnitTest unitTest = (UnitTest) o;
    return Objects.equals(this.id, unitTest.id) &&
        Objects.equals(this.input, unitTest.input) &&
        Objects.equals(this.outputValue, unitTest.outputValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, input, outputValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitTest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    outputValue: ").append(toIndentedString(outputValue)).append("\n");
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

