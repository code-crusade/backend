package com.etsmtl.codecrusade.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="submission")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	private String code;

	@ManyToOne
	@JoinColumn(name = "submission_id")
	private Exercise exercise;

	@ManyToOne
	private Student student;
}
