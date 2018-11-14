package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.Parameter;
import com.etsmtl.codecrusade.model.SupportedType;
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
 * Template
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-14T14:33:02.883045400-05:00[America/New_York]")

public class Template   {
  @JsonProperty("className")
  private String className;

  @JsonProperty("functionName")
  private String functionName;

  @JsonProperty("params")
  @Valid
  private List<Parameter> params = new ArrayList<>();

  @JsonProperty("functionReturnValue")
  private Object functionReturnValue = null;

  @JsonProperty("functionReturnType")
  private SupportedType functionReturnType = null;

  @JsonProperty("prependedCode")
  @Valid
  private Map<String, String> prependedCode = new HashMap<>();

  @JsonProperty("appendedCode")
  @Valid
  private Map<String, String> appendedCode = new HashMap<>();

  public Template className(String className) {
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

  public Template functionName(String functionName) {
    this.functionName = functionName;
    return this;
  }

  /**
   * Get functionName
   * @return functionName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getFunctionName() {
    return functionName;
  }

  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  public Template params(List<Parameter> params) {
    this.params = params;
    return this;
  }

  public Template addParamsItem(Parameter paramsItem) {
    this.params.add(paramsItem);
    return this;
  }

  /**
   * Get params
   * @return params
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Parameter> getParams() {
    return params;
  }

  public void setParams(List<Parameter> params) {
    this.params = params;
  }

  public Template functionReturnValue(Object functionReturnValue) {
    this.functionReturnValue = functionReturnValue;
    return this;
  }

  /**
   * Type encompassing any value type
   * @return functionReturnValue
  **/
  @ApiModelProperty(required = true, value = "Type encompassing any value type")
  @NotNull


  public Object getFunctionReturnValue() {
    return functionReturnValue;
  }

  public void setFunctionReturnValue(Object functionReturnValue) {
    this.functionReturnValue = functionReturnValue;
  }

  public Template functionReturnType(SupportedType functionReturnType) {
    this.functionReturnType = functionReturnType;
    return this;
  }

  /**
   * Get functionReturnType
   * @return functionReturnType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public SupportedType getFunctionReturnType() {
    return functionReturnType;
  }

  public void setFunctionReturnType(SupportedType functionReturnType) {
    this.functionReturnType = functionReturnType;
  }

  public Template prependedCode(Map<String, String> prependedCode) {
    this.prependedCode = prependedCode;
    return this;
  }

  public Template putPrependedCodeItem(String key, String prependedCodeItem) {
    this.prependedCode.put(key, prependedCodeItem);
    return this;
  }

  /**
   * Get prependedCode
   * @return prependedCode
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Map<String, String> getPrependedCode() {
    return prependedCode;
  }

  public void setPrependedCode(Map<String, String> prependedCode) {
    this.prependedCode = prependedCode;
  }

  public Template appendedCode(Map<String, String> appendedCode) {
    this.appendedCode = appendedCode;
    return this;
  }

  public Template putAppendedCodeItem(String key, String appendedCodeItem) {
    this.appendedCode.put(key, appendedCodeItem);
    return this;
  }

  /**
   * Get appendedCode
   * @return appendedCode
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Map<String, String> getAppendedCode() {
    return appendedCode;
  }

  public void setAppendedCode(Map<String, String> appendedCode) {
    this.appendedCode = appendedCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Template template = (Template) o;
    return Objects.equals(this.className, template.className) &&
        Objects.equals(this.functionName, template.functionName) &&
        Objects.equals(this.params, template.params) &&
        Objects.equals(this.functionReturnValue, template.functionReturnValue) &&
        Objects.equals(this.functionReturnType, template.functionReturnType) &&
        Objects.equals(this.prependedCode, template.prependedCode) &&
        Objects.equals(this.appendedCode, template.appendedCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, functionName, params, functionReturnValue, functionReturnType, prependedCode, appendedCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Template {\n");
    
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    functionName: ").append(toIndentedString(functionName)).append("\n");
    sb.append("    params: ").append(toIndentedString(params)).append("\n");
    sb.append("    functionReturnValue: ").append(toIndentedString(functionReturnValue)).append("\n");
    sb.append("    functionReturnType: ").append(toIndentedString(functionReturnType)).append("\n");
    sb.append("    prependedCode: ").append(toIndentedString(prependedCode)).append("\n");
    sb.append("    appendedCode: ").append(toIndentedString(appendedCode)).append("\n");
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

