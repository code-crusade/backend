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
 * EntryPointFunctionParams
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-05T19:13:49.931153400-05:00[America/New_York]")

public class EntryPointFunctionParams   {
  @JsonProperty("name")
  private String name;

  @JsonProperty("type")
  private SupportedType type = null;

  public EntryPointFunctionParams name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EntryPointFunctionParams type(SupportedType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")

  @Valid

  public SupportedType getType() {
    return type;
  }

  public void setType(SupportedType type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntryPointFunctionParams entryPointFunctionParams = (EntryPointFunctionParams) o;
    return Objects.equals(this.name, entryPointFunctionParams.name) &&
        Objects.equals(this.type, entryPointFunctionParams.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntryPointFunctionParams {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

