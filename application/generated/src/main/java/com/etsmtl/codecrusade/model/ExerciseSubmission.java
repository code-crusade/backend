package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.RunnerArguments;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExerciseSubmission
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-07T12:58:45.826018600-05:00[America/New_York]")

public class ExerciseSubmission   {
  @JsonProperty("submissionId")
  private Integer submissionId;

  @JsonProperty("exerciseId")
  private Integer exerciseId;

  @JsonProperty("userId")
  private Integer userId;

  @JsonProperty("program")
  private RunnerArguments program = null;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt;

  public ExerciseSubmission submissionId(Integer submissionId) {
    this.submissionId = submissionId;
    return this;
  }

  /**
   * Get submissionId
   * @return submissionId
  **/
  @ApiModelProperty(value = "")


  public Integer getSubmissionId() {
    return submissionId;
  }

  public void setSubmissionId(Integer submissionId) {
    this.submissionId = submissionId;
  }

  public ExerciseSubmission exerciseId(Integer exerciseId) {
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

  public ExerciseSubmission userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public ExerciseSubmission program(RunnerArguments program) {
    this.program = program;
    return this;
  }

  /**
   * Get program
   * @return program
  **/
  @ApiModelProperty(value = "")

  @Valid

  public RunnerArguments getProgram() {
    return program;
  }

  public void setProgram(RunnerArguments program) {
    this.program = program;
  }

  public ExerciseSubmission createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExerciseSubmission exerciseSubmission = (ExerciseSubmission) o;
    return Objects.equals(this.submissionId, exerciseSubmission.submissionId) &&
        Objects.equals(this.exerciseId, exerciseSubmission.exerciseId) &&
        Objects.equals(this.userId, exerciseSubmission.userId) &&
        Objects.equals(this.program, exerciseSubmission.program) &&
        Objects.equals(this.createdAt, exerciseSubmission.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(submissionId, exerciseId, userId, program, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExerciseSubmission {\n");
    
    sb.append("    submissionId: ").append(toIndentedString(submissionId)).append("\n");
    sb.append("    exerciseId: ").append(toIndentedString(exerciseId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    program: ").append(toIndentedString(program)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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

