package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.CodeValidationReportResults;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CodeValidationReport
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-07T12:58:45.826018600-05:00[America/New_York]")

public class CodeValidationReport   {
  @JsonProperty("exerciseId")
  private Integer exerciseId;

  @JsonProperty("results")
  @Valid
  private List<CodeValidationReportResults> results = null;

  public CodeValidationReport exerciseId(Integer exerciseId) {
    this.exerciseId = exerciseId;
    return this;
  }

  /**
   * Get exerciseId
   * @return exerciseId
  **/
  @ApiModelProperty(value = "")


  public Integer getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(Integer exerciseId) {
    this.exerciseId = exerciseId;
  }

  public CodeValidationReport results(List<CodeValidationReportResults> results) {
    this.results = results;
    return this;
  }

  public CodeValidationReport addResultsItem(CodeValidationReportResults resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Get results
   * @return results
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CodeValidationReportResults> getResults() {
    return results;
  }

  public void setResults(List<CodeValidationReportResults> results) {
    this.results = results;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CodeValidationReport codeValidationReport = (CodeValidationReport) o;
    return Objects.equals(this.exerciseId, codeValidationReport.exerciseId) &&
        Objects.equals(this.results, codeValidationReport.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exerciseId, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CodeValidationReport {\n");
    
    sb.append("    exerciseId: ").append(toIndentedString(exerciseId)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
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

