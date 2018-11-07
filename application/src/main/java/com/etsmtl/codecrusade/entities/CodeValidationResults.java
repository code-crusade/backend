package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.ObjectAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "code_validation_results")
@Getter
@Setter
@NoArgsConstructor
public class CodeValidationResults {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;

	@Column(name = "passed")
	private Boolean passed;

	@ManyToOne
	@JoinColumn(name = "test_id",
				foreignKey = @ForeignKey(name = "fk_test_id"))
	private CodeValidation test;

	@Column(name = "expectedOutput")
	@Convert(converter = ObjectAttributeConverter.class)
	private Object actualOutput;

	@ManyToOne
	@JoinColumn(name = "submission_id",
				foreignKey = @ForeignKey(name = "fk_submission_id"))
	private Submission submission;
}
