package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.StringListAttributeConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "exercise")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Column(name="title")
	private String title;

	@ElementCollection
	@MapKeyColumn(name = "lang")
	@Column(name = "value")
	@CollectionTable(name = "exercise_descriptions",
					 joinColumns = @JoinColumn(name = "exerciseId",
											   foreignKey = @ForeignKey(name = "fk_exercise_id")))
	private Map<String, String> description;

	@Convert(converter = StringListAttributeConverter.class)
	@Column(name = "supportedLanguages")
	private List<String> supportedLanguages;

	@OneToOne
	@JoinColumn(name = "exrecise_id",
				foreignKey = @ForeignKey(name = "fk_template_id"))
	private Template template;

	@OneToMany(mappedBy = "exercise")
	private List<CodeValidation> tests;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
}
