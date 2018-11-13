package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CodeValidationReportResults
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-13T11:55:45.840765800-05:00[America/New_York]")

public class CodeValidationReportResults   {
  @JsonProperty("testName")
  private String testName;

  @JsonProperty("passed")
  private Boolean passed;

  @JsonProperty("inputParameters")
  @Valid
  private List<Object> inputParameters = null;

  @JsonProperty("expectedOutput")
  private Object expectedOutput = null;

  @JsonProperty("actualOutput")
  private Object actualOutput = null;

  public CodeValidationReportResults testName(String testName) {
    this.testName = testName;
    return this;
  }

  /**
   * Get testName
   * @return testName
  **/
  @ApiModelProperty(value = "")


  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public CodeValidationReportResults passed(Boolean passed) {
    this.passed = passed;
    return this;
  }

  /**
   * Get passed
   * @return passed
  **/
  @ApiModelProperty(value = "")


  public Boolean getPassed() {
    return passed;
  }

  public void setPassed(Boolean passed) {
    this.passed = passed;
  }

  public CodeValidationReportResults inputParameters(List<Object> inputParameters) {
    this.inputParameters = inputParameters;
    return this;
  }

  public CodeValidationReportResults addInputParametersItem(Object inputParametersItem) {
    if (this.inputParameters == null) {
      this.inputParameters = new ArrayList<>();
    }
    this.inputParameters.add(inputParametersItem);
    return this;
  }

  /**
   * Get inputParameters
   * @return inputParameters
  **/
  @ApiModelProperty(value = "")


  public List<Object> getInputParameters() {
    return inputParameters;
  }

  public void setInputParameters(List<Object> inputParameters) {
    this.inputParameters = inputParameters;
  }

  public CodeValidationReportResults expectedOutput(Object expectedOutput) {
    this.expectedOutput = expectedOutput;
    return this;
  }

  /**
   * Get expectedOutput
   * @return expectedOutput
  **/
  @ApiModelProperty(value = "")


  public Object getExpectedOutput() {
    return expectedOutput;
  }

  public void setExpectedOutput(Object expectedOutput) {
    this.expectedOutput = expectedOutput;
  }

  public CodeValidationReportResults actualOutput(Object actualOutput) {
    this.actualOutput = actualOutput;
    return this;
  }

  /**
   * Get actualOutput
   * @return actualOutput
  **/
  @ApiModelProperty(value = "")


  public Object getActualOutput() {
    return actualOutput;
  }

  public void setActualOutput(Object actualOutput) {
    this.actualOutput = actualOutput;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CodeValidationReportResults codeValidationReportResults = (CodeValidationReportResults) o;
    return Objects.equals(this.testName, codeValidationReportResults.testName) &&
        Objects.equals(this.passed, codeValidationReportResults.passed) &&
        Objects.equals(this.inputParameters, codeValidationReportResults.inputParameters) &&
        Objects.equals(this.expectedOutput, codeValidationReportResults.expectedOutput) &&
        Objects.equals(this.actualOutput, codeValidationReportResults.actualOutput);
  }

  @Override
  public int hashCode() {
    return Objects.hash(testName, passed, inputParameters, expectedOutput, actualOutput);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CodeValidationReportResults {\n");
    
    sb.append("    testName: ").append(toIndentedString(testName)).append("\n");
    sb.append("    passed: ").append(toIndentedString(passed)).append("\n");
    sb.append("    inputParameters: ").append(toIndentedString(inputParameters)).append("\n");
    sb.append("    expectedOutput: ").append(toIndentedString(expectedOutput)).append("\n");
    sb.append("    actualOutput: ").append(toIndentedString(actualOutput)).append("\n");
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

