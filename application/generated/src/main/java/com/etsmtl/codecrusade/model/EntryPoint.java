package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.EntryPointFunctionParams;
import com.etsmtl.codecrusade.model.SupportedType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EntryPoint
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-05T19:13:49.931153400-05:00[America/New_York]")

public class EntryPoint   {
  @JsonProperty("className")
  private String className;

  @JsonProperty("functionName")
  private String functionName;

  @JsonProperty("functionParams")
  @Valid
  private List<EntryPointFunctionParams> functionParams = null;

  @JsonProperty("returnType")
  private SupportedType returnType = null;

  public EntryPoint className(String className) {
    this.className = className;
    return this;
  }

  /**
   * Get className
   * @return className
  **/
  @ApiModelProperty(value = "")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public EntryPoint functionName(String functionName) {
    this.functionName = functionName;
    return this;
  }

  /**
   * Get functionName
   * @return functionName
  **/
  @ApiModelProperty(value = "")


  public String getFunctionName() {
    return functionName;
  }

  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  public EntryPoint functionParams(List<EntryPointFunctionParams> functionParams) {
    this.functionParams = functionParams;
    return this;
  }

  public EntryPoint addFunctionParamsItem(EntryPointFunctionParams functionParamsItem) {
    if (this.functionParams == null) {
      this.functionParams = new ArrayList<>();
    }
    this.functionParams.add(functionParamsItem);
    return this;
  }

  /**
   * Get functionParams
   * @return functionParams
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<EntryPointFunctionParams> getFunctionParams() {
    return functionParams;
  }

  public void setFunctionParams(List<EntryPointFunctionParams> functionParams) {
    this.functionParams = functionParams;
  }

  public EntryPoint returnType(SupportedType returnType) {
    this.returnType = returnType;
    return this;
  }

  /**
   * Get returnType
   * @return returnType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public SupportedType getReturnType() {
    return returnType;
  }

  public void setReturnType(SupportedType returnType) {
    this.returnType = returnType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntryPoint entryPoint = (EntryPoint) o;
    return Objects.equals(this.className, entryPoint.className) &&
        Objects.equals(this.functionName, entryPoint.functionName) &&
        Objects.equals(this.functionParams, entryPoint.functionParams) &&
        Objects.equals(this.returnType, entryPoint.returnType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, functionName, functionParams, returnType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntryPoint {\n");
    
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    functionName: ").append(toIndentedString(functionName)).append("\n");
    sb.append("    functionParams: ").append(toIndentedString(functionParams)).append("\n");
    sb.append("    returnType: ").append(toIndentedString(returnType)).append("\n");
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

