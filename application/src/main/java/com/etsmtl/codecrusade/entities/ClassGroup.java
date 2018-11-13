package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classGroup")
@Data
@Builder
@AllArgsConstructor
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

	@Column(name = "class")
	private String propertyClass;

	@Enumerated(EnumType.STRING)
	private Semester semester;

	@Column(name = "year")
	private BigDecimal year;

	@Column(name = "archived")
	private Boolean archived;
}
