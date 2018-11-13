package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.Difficulties;
import com.etsmtl.codecrusade.model.IntlString;
import com.etsmtl.codecrusade.model.Template;
import com.etsmtl.codecrusade.model.TestCase;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Exercise
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-12T18:01:20.447505100-05:00[America/New_York]")

public class Exercise   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("title")
  private IntlString title = null;

  @JsonProperty("description")
  private IntlString description = null;

  @JsonProperty("difficulty")
  private Difficulties difficulty = null;

  @JsonProperty("template")
  private Template template = null;

  @JsonProperty("supportedLanguages")
  @Valid
  private List<String> supportedLanguages = null;

  @JsonProperty("sampleTestCases")
  @Valid
  private List<TestCase> sampleTestCases = new ArrayList<>();

  public Exercise id(Integer id) {
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

  public Exercise title(IntlString title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public IntlString getTitle() {
    return title;
  }

  public void setTitle(IntlString title) {
    this.title = title;
  }

  public Exercise description(IntlString description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public IntlString getDescription() {
    return description;
  }

  public void setDescription(IntlString description) {
    this.description = description;
  }

  public Exercise difficulty(Difficulties difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * Get difficulty
   * @return difficulty
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Difficulties getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulties difficulty) {
    this.difficulty = difficulty;
  }

  public Exercise template(Template template) {
    this.template = template;
    return this;
  }

  /**
   * Get template
   * @return template
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Template getTemplate() {
    return template;
  }

  public void setTemplate(Template template) {
    this.template = template;
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

  public Exercise sampleTestCases(List<TestCase> sampleTestCases) {
    this.sampleTestCases = sampleTestCases;
    return this;
  }

  public Exercise addSampleTestCasesItem(TestCase sampleTestCasesItem) {
    this.sampleTestCases.add(sampleTestCasesItem);
    return this;
  }

  /**
   * Get sampleTestCases
   * @return sampleTestCases
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<TestCase> getSampleTestCases() {
    return sampleTestCases;
  }

  public void setSampleTestCases(List<TestCase> sampleTestCases) {
    this.sampleTestCases = sampleTestCases;
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
    return Objects.equals(this.id, exercise.id) &&
        Objects.equals(this.title, exercise.title) &&
        Objects.equals(this.description, exercise.description) &&
        Objects.equals(this.difficulty, exercise.difficulty) &&
        Objects.equals(this.template, exercise.template) &&
        Objects.equals(this.supportedLanguages, exercise.supportedLanguages) &&
        Objects.equals(this.sampleTestCases, exercise.sampleTestCases);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, difficulty, template, supportedLanguages, sampleTestCases);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Exercise {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    supportedLanguages: ").append(toIndentedString(supportedLanguages)).append("\n");
    sb.append("    sampleTestCases: ").append(toIndentedString(sampleTestCases)).append("\n");
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

