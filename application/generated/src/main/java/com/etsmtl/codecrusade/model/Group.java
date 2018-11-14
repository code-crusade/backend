package com.etsmtl.codecrusade.model;

import java.util.Objects;
import com.etsmtl.codecrusade.model.Semesters;
import com.etsmtl.codecrusade.model.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Group of students
 */
@ApiModel(description = "Group of students")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-14T14:33:02.883045400-05:00[America/New_York]")

public class Group   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("groupNumber")
  private Integer groupNumber;

  @JsonProperty("class")
  private String propertyClass;

  @JsonProperty("semester")
  private Semesters semester = null;

  @JsonProperty("year")
  private BigDecimal year;

  @JsonProperty("students")
  @Valid
  private List<Student> students = new ArrayList<>();

  @JsonProperty("students_ids")
  @Valid
  private List<Integer> studentsIds = null;

  @JsonProperty("archived")
  private Boolean archived;

  public Group id(Integer id) {
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

  public Group groupNumber(Integer groupNumber) {
    this.groupNumber = groupNumber;
    return this;
  }

  /**
   * Get groupNumber
   * @return groupNumber
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getGroupNumber() {
    return groupNumber;
  }

  public void setGroupNumber(Integer groupNumber) {
    this.groupNumber = groupNumber;
  }

  public Group propertyClass(String propertyClass) {
    this.propertyClass = propertyClass;
    return this;
  }

  /**
   * Get propertyClass
   * @return propertyClass
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPropertyClass() {
    return propertyClass;
  }

  public void setPropertyClass(String propertyClass) {
    this.propertyClass = propertyClass;
  }

  public Group semester(Semesters semester) {
    this.semester = semester;
    return this;
  }

  /**
   * Get semester
   * @return semester
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Semesters getSemester() {
    return semester;
  }

  public void setSemester(Semesters semester) {
    this.semester = semester;
  }

  public Group year(BigDecimal year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getYear() {
    return year;
  }

  public void setYear(BigDecimal year) {
    this.year = year;
  }

  public Group students(List<Student> students) {
    this.students = students;
    return this;
  }

  public Group addStudentsItem(Student studentsItem) {
    this.students.add(studentsItem);
    return this;
  }

  /**
   * Get students
   * @return students
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
  @NotNull

  @Valid

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public Group studentsIds(List<Integer> studentsIds) {
    this.studentsIds = studentsIds;
    return this;
  }

  public Group addStudentsIdsItem(Integer studentsIdsItem) {
    if (this.studentsIds == null) {
      this.studentsIds = new ArrayList<>();
    }
    this.studentsIds.add(studentsIdsItem);
    return this;
  }

  /**
   * Get studentsIds
   * @return studentsIds
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getStudentsIds() {
    return studentsIds;
  }

  public void setStudentsIds(List<Integer> studentsIds) {
    this.studentsIds = studentsIds;
  }

  public Group archived(Boolean archived) {
    this.archived = archived;
    return this;
  }

  /**
   * Get archived
   * @return archived
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getArchived() {
    return archived;
  }

  public void setArchived(Boolean archived) {
    this.archived = archived;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Group group = (Group) o;
    return Objects.equals(this.id, group.id) &&
        Objects.equals(this.groupNumber, group.groupNumber) &&
        Objects.equals(this.propertyClass, group.propertyClass) &&
        Objects.equals(this.semester, group.semester) &&
        Objects.equals(this.year, group.year) &&
        Objects.equals(this.students, group.students) &&
        Objects.equals(this.studentsIds, group.studentsIds) &&
        Objects.equals(this.archived, group.archived);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, groupNumber, propertyClass, semester, year, students, studentsIds, archived);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Group {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    groupNumber: ").append(toIndentedString(groupNumber)).append("\n");
    sb.append("    propertyClass: ").append(toIndentedString(propertyClass)).append("\n");
    sb.append("    semester: ").append(toIndentedString(semester)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    students: ").append(toIndentedString(students)).append("\n");
    sb.append("    studentsIds: ").append(toIndentedString(studentsIds)).append("\n");
    sb.append("    archived: ").append(toIndentedString(archived)).append("\n");
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

