package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="classGroup")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;

	@OneToMany
	@JoinTable(name="classGroup_students")
	private Set<Student> students;
}
