package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IntlString
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-13T11:55:45.840765800-05:00[America/New_York]")

public class IntlString   {
  @JsonProperty("fr")
  private String fr;

  @JsonProperty("en")
  private String en;

  public IntlString fr(String fr) {
    this.fr = fr;
    return this;
  }

  /**
   * Get fr
   * @return fr
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getFr() {
    return fr;
  }

  public void setFr(String fr) {
    this.fr = fr;
  }

  public IntlString en(String en) {
    this.en = en;
    return this;
  }

  /**
   * Get en
   * @return en
  **/
  @ApiModelProperty(value = "")


  public String getEn() {
    return en;
  }

  public void setEn(String en) {
    this.en = en;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntlString intlString = (IntlString) o;
    return Objects.equals(this.fr, intlString.fr) &&
        Objects.equals(this.en, intlString.en);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fr, en);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntlString {\n");
    
    sb.append("    fr: ").append(toIndentedString(fr)).append("\n");
    sb.append("    en: ").append(toIndentedString(en)).append("\n");
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

