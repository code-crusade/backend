package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classGroup")
@Data
@NoArgsConstructor
public class ClassGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "classGroup_id",
                foreignKey = @ForeignKey(name = "fk_classGroup_id"))
    private List<Student> students = new ArrayList<>();

    @Column(name = "groupNumber")
    private Integer groupNumber;

    @Column(name = "course")
    @Size(max = 50)
    private String course;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(name = "year")
    private BigDecimal year;

    @Column(name = "archived")
    private Boolean archived;

    @Builder
    public ClassGroup(List<Student> students, Integer groupNumber, String course, Semester semester, BigDecimal year, Boolean archived) {
        this.students = students;
        this.groupNumber = groupNumber;
        this.course = course;
        this.semester = semester;
        this.year = year;
        this.archived = archived;
    }
}
