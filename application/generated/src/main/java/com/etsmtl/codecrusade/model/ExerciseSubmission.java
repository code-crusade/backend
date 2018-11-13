package com.etsmtl.codecrusade.model;

import java.util.Objects;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-13T11:55:45.840765800-05:00[America/New_York]")

public class ExerciseSubmission   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("exerciseId")
  private Integer exerciseId;

  @JsonProperty("userId")
  private Integer userId;

  @JsonProperty("code")
  private String code;

  @JsonProperty("language")
  private Object language = null;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt;

  public ExerciseSubmission id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
  @NotNull


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ExerciseSubmission exerciseId(Integer exerciseId) {
    this.exerciseId = exerciseId;
    return this;
  }

  /**
   * Get exerciseId
   * @return exerciseId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


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
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public ExerciseSubmission code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ExerciseSubmission language(Object language) {
    this.language = language;
    return this;
  }

  /**
   * Get language
   * @return language
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Object getLanguage() {
    return language;
  }

  public void setLanguage(Object language) {
    this.language = language;
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
    return Objects.equals(this.id, exerciseSubmission.id) &&
        Objects.equals(this.exerciseId, exerciseSubmission.exerciseId) &&
        Objects.equals(this.userId, exerciseSubmission.userId) &&
        Objects.equals(this.code, exerciseSubmission.code) &&
        Objects.equals(this.language, exerciseSubmission.language) &&
        Objects.equals(this.createdAt, exerciseSubmission.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, exerciseId, userId, code, language, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExerciseSubmission {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    exerciseId: ").append(toIndentedString(exerciseId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
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

