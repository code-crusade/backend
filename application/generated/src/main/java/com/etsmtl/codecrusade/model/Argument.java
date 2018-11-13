package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.SupportedType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An argument
 */
@ApiModel(description = "An argument")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-13T11:55:45.840765800-05:00[America/New_York]")

public class Argument   {
  @JsonProperty("type")
  private SupportedType type = null;

  @JsonProperty("value")
  private Object value = null;

  public Argument type(SupportedType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public SupportedType getType() {
    return type;
  }

  public void setType(SupportedType type) {
    this.type = type;
  }

  public Argument value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * Type encompassing any value type
   * @return value
  **/
  @ApiModelProperty(required = true, value = "Type encompassing any value type")
  @NotNull


  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Argument argument = (Argument) o;
    return Objects.equals(this.type, argument.type) &&
        Objects.equals(this.value, argument.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Argument {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

