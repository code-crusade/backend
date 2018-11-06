package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.EntryPoint;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Exercise
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-05T19:13:49.931153400-05:00[America/New_York]")

public class Exercise   {
  @JsonProperty("exerciseId")
  private Integer exerciseId;

  @JsonProperty("title")
  @Valid
  private Map<String, String> title = null;

  @JsonProperty("description")
  @Valid
  private Map<String, String> description = null;

  @JsonProperty("entryPoint")
  private EntryPoint entryPoint = null;

  @JsonProperty("supportedLanguages")
  @Valid
  private List<String> supportedLanguages = null;

  @JsonProperty("codeTemplates")
  @Valid
  private Map<String, String> codeTemplates = null;

  public Exercise exerciseId(Integer exerciseId) {
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

  public Exercise title(Map<String, String> title) {
    this.title = title;
    return this;
  }

  public Exercise putTitleItem(String key, String titleItem) {
    if (this.title == null) {
      this.title = new HashMap<>();
    }
    this.title.put(key, titleItem);
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getTitle() {
    return title;
  }

  public void setTitle(Map<String, String> title) {
    this.title = title;
  }

  public Exercise description(Map<String, String> description) {
    this.description = description;
    return this;
  }

  public Exercise putDescriptionItem(String key, String descriptionItem) {
    if (this.description == null) {
      this.description = new HashMap<>();
    }
    this.description.put(key, descriptionItem);
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getDescription() {
    return description;
  }

  public void setDescription(Map<String, String> description) {
    this.description = description;
  }

  public Exercise entryPoint(EntryPoint entryPoint) {
    this.entryPoint = entryPoint;
    return this;
  }

  /**
   * Get entryPoint
   * @return entryPoint
  **/
  @ApiModelProperty(value = "")

  @Valid

  public EntryPoint getEntryPoint() {
    return entryPoint;
  }

  public void setEntryPoint(EntryPoint entryPoint) {
    this.entryPoint = entryPoint;
  }

  public Exercise supportedLanguages(List<String> supportedLanguages) {
    this.supportedLanguages = supportedLanguages;
    return this;
  }

  public Exercise addSupportedLanguagesItem(String supportedLanguagesItem) {
    if (this.supportedLanguages == null) {
      this.supportedLanguages = new ArrayList<>();
    }
    this.supportedLanguages.add(supportedLanguagesItem);
    return this;
  }

  /**
   * Get supportedLanguages
   * @return supportedLanguages
  **/
  @ApiModelProperty(value = "")


  public List<String> getSupportedLanguages() {
    return supportedLanguages;
  }

  public void setSupportedLanguages(List<String> supportedLanguages) {
    this.supportedLanguages = supportedLanguages;
  }

  public Exercise codeTemplates(Map<String, String> codeTemplates) {
    this.codeTemplates = codeTemplates;
    return this;
  }

  public Exercise putCodeTemplatesItem(String key, String codeTemplatesItem) {
    if (this.codeTemplates == null) {
      this.codeTemplates = new HashMap<>();
    }
    this.codeTemplates.put(key, codeTemplatesItem);
    return this;
  }

  /**
   * Get codeTemplates
   * @return codeTemplates
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getCodeTemplates() {
    return codeTemplates;
  }

  public void setCodeTemplates(Map<String, String> codeTemplates) {
    this.codeTemplates = codeTemplates;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Exercise exercise = (Exercise) o;
    return Objects.equals(this.exerciseId, exercise.exerciseId) &&
        Objects.equals(this.title, exercise.title) &&
        Objects.equals(this.description, exercise.description) &&
        Objects.equals(this.entryPoint, exercise.entryPoint) &&
        Objects.equals(this.supportedLanguages, exercise.supportedLanguages) &&
        Objects.equals(this.codeTemplates, exercise.codeTemplates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exerciseId, title, description, entryPoint, supportedLanguages, codeTemplates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Exercise {\n");
    
    sb.append("    exerciseId: ").append(toIndentedString(exerciseId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    entryPoint: ").append(toIndentedString(entryPoint)).append("\n");
    sb.append("    supportedLanguages: ").append(toIndentedString(supportedLanguages)).append("\n");
    sb.append("    codeTemplates: ").append(toIndentedString(codeTemplates)).append("\n");
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

