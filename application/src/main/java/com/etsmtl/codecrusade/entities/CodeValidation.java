package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.ObjectAttributeConverter;
import com.etsmtl.codecrusade.entities.converters.ObjectListAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
@NoArgsConstructor
public class CodeValidation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;

	@Column(name = "testName")
	private String testName;

	@Column(name = "passed")
	private Boolean passed;

	@Lob
	@Column(name = "inputParameters")
	@Convert(converter = ObjectListAttributeConverter.class)
	private List<Object> inputParameters;

	@Lob
	@Column(name = "expectedOutput")
	@Convert(converter = ObjectAttributeConverter.class)
	private Object expectedOutput;

	@ManyToOne
	@JoinColumn(name = "exercise_id",
				foreignKey = @ForeignKey(name = "fk_exercise_id"))
	private Exercise exercise;
}
