package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A student user
 */
@ApiModel(description = "A student user")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-14T14:33:02.883045400-05:00[America/New_York]")

public class Student   {
  @JsonProperty("accessCode")
  private Integer accessCode;

  @JsonProperty("firstName")
  private Integer firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("email")
  private String email;

  @JsonProperty("totalExercisesSuccessed")
  private BigDecimal totalExercisesSuccessed;

  @JsonProperty("totalExercisesDone")
  private BigDecimal totalExercisesDone;

  public Student accessCode(Integer accessCode) {
    this.accessCode = accessCode;
    return this;
  }

  /**
   * Get accessCode
   * @return accessCode
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getAccessCode() {
    return accessCode;
  }

  public void setAccessCode(Integer accessCode) {
    this.accessCode = accessCode;
  }

  public Student firstName(Integer firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getFirstName() {
    return firstName;
  }

  public void setFirstName(Integer firstName) {
    this.firstName = firstName;
  }

  public Student lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Student email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Student totalExercisesSuccessed(BigDecimal totalExercisesSuccessed) {
    this.totalExercisesSuccessed = totalExercisesSuccessed;
    return this;
  }

  /**
   * Get totalExercisesSuccessed
   * @return totalExercisesSuccessed
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getTotalExercisesSuccessed() {
    return totalExercisesSuccessed;
  }

  public void setTotalExercisesSuccessed(BigDecimal totalExercisesSuccessed) {
    this.totalExercisesSuccessed = totalExercisesSuccessed;
  }

  public Student totalExercisesDone(BigDecimal totalExercisesDone) {
    this.totalExercisesDone = totalExercisesDone;
    return this;
  }

  /**
   * Get totalExercisesDone
   * @return totalExercisesDone
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getTotalExercisesDone() {
    return totalExercisesDone;
  }

  public void setTotalExercisesDone(BigDecimal totalExercisesDone) {
    this.totalExercisesDone = totalExercisesDone;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(this.accessCode, student.accessCode) &&
        Objects.equals(this.firstName, student.firstName) &&
        Objects.equals(this.lastName, student.lastName) &&
        Objects.equals(this.email, student.email) &&
        Objects.equals(this.totalExercisesSuccessed, student.totalExercisesSuccessed) &&
        Objects.equals(this.totalExercisesDone, student.totalExercisesDone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessCode, firstName, lastName, email, totalExercisesSuccessed, totalExercisesDone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Student {\n");
    
    sb.append("    accessCode: ").append(toIndentedString(accessCode)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    totalExercisesSuccessed: ").append(toIndentedString(totalExercisesSuccessed)).append("\n");
    sb.append("    totalExercisesDone: ").append(toIndentedString(totalExercisesDone)).append("\n");
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

