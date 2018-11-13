package com.etsmtl.codecrusade.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Type encompassing any value type
 */
@ApiModel(description = "Type encompassing any value type")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-12T18:01:20.447505100-05:00[America/New_York]")

public class AnyValue   {

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnyValue {\n");
    
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

