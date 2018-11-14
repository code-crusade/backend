package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.Assertion;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Test case data structure used to generate examplary test cases
 */
@ApiModel(description = "Test case data structure used to generate examplary test cases")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-14T14:33:02.883045400-05:00[America/New_York]")

public class TestCase   {
  @JsonProperty("it")
  private String it;

  @JsonProperty("assertions")
  @Valid
  private List<Assertion> assertions = new ArrayList<>();

  public TestCase it(String it) {
    this.it = it;
    return this;
  }

  /**
   * Get it
   * @return it
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getIt() {
    return it;
  }

  public void setIt(String it) {
    this.it = it;
  }

  public TestCase assertions(List<Assertion> assertions) {
    this.assertions = assertions;
    return this;
  }

  public TestCase addAssertionsItem(Assertion assertionsItem) {
    this.assertions.add(assertionsItem);
    return this;
  }

  /**
   * Get assertions
   * @return assertions
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Assertion> getAssertions() {
    return assertions;
  }

  public void setAssertions(List<Assertion> assertions) {
    this.assertions = assertions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestCase testCase = (TestCase) o;
    return Objects.equals(this.it, testCase.it) &&
        Objects.equals(this.assertions, testCase.assertions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(it, assertions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TestCase {\n");
    
    sb.append("    it: ").append(toIndentedString(it)).append("\n");
    sb.append("    assertions: ").append(toIndentedString(assertions)).append("\n");
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

