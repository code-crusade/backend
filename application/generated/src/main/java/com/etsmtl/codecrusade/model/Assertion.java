package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.Argument;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An assertion to be included in a test
 */
@ApiModel(description = "An assertion to be included in a test")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-12T18:01:20.447505100-05:00[America/New_York]")

public class Assertion   {
  @JsonProperty("inputArguments")
  @Valid
  private List<Argument> inputArguments = new ArrayList<>();

  @JsonProperty("expectedOutput")
  private Argument expectedOutput = null;

  public Assertion inputArguments(List<Argument> inputArguments) {
    this.inputArguments = inputArguments;
    return this;
  }

  public Assertion addInputArgumentsItem(Argument inputArgumentsItem) {
    this.inputArguments.add(inputArgumentsItem);
    return this;
  }

  /**
   * Get inputArguments
   * @return inputArguments
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Argument> getInputArguments() {
    return inputArguments;
  }

  public void setInputArguments(List<Argument> inputArguments) {
    this.inputArguments = inputArguments;
  }

  public Assertion expectedOutput(Argument expectedOutput) {
    this.expectedOutput = expectedOutput;
    return this;
  }

  /**
   * Get expectedOutput
   * @return expectedOutput
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Argument getExpectedOutput() {
    return expectedOutput;
  }

  public void setExpectedOutput(Argument expectedOutput) {
    this.expectedOutput = expectedOutput;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Assertion assertion = (Assertion) o;
    return Objects.equals(this.inputArguments, assertion.inputArguments) &&
        Objects.equals(this.expectedOutput, assertion.expectedOutput);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputArguments, expectedOutput);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Assertion {\n");
    
    sb.append("    inputArguments: ").append(toIndentedString(inputArguments)).append("\n");
    sb.append("    expectedOutput: ").append(toIndentedString(expectedOutput)).append("\n");
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

