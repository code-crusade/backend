package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RunnerArguments
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-13T11:55:45.840765800-05:00[America/New_York]")

public class RunnerArguments   {
  @JsonProperty("code")
  private String code;

  @JsonProperty("language")
  private String language;

  public RunnerArguments code(String code) {
    this.code = code;
    return this;
  }

  /**
   * code
   * @return code
  **/
  @ApiModelProperty(value = "code")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public RunnerArguments language(String language) {
    this.language = language;
    return this;
  }

  /**
   * language
   * @return language
  **/
  @ApiModelProperty(value = "language")


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunnerArguments runnerArguments = (RunnerArguments) o;
    return Objects.equals(this.code, runnerArguments.code) &&
        Objects.equals(this.language, runnerArguments.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, language);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunnerArguments {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
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

