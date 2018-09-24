package com.etsmtl.codecrusade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="exercise")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	@NotBlank
	@Column(name="unit_tests")
	private String unitTests;

	@Column(name="title")
	private String title;

	@Column(name="description")
	private String description;
}
