package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.StringListAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="exercise")
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

	@ElementCollection
	@MapKeyColumn(name="lang")
	@Column(name="value")
	@CollectionTable(name="exercise_titles", joinColumns=@JoinColumn(name="exerciseId"))
	private Map<String,String> title;

	@ElementCollection
	@MapKeyColumn(name="lang")
	@Column(name="value")
	@CollectionTable(name="exercise_descriptions", joinColumns=@JoinColumn(name="exerciseId"))
	private Map<String,String> description;

	@OneToOne
	@JoinColumn(name="exercise_id")
	private EntryPoint entryPoint;

	@Convert(converter = StringListAttributeConverter.class)
	@Column(name = "supportedLanguages")
	private List<String> supportedLanguages;

	@ElementCollection
	@MapKeyColumn(name="lang")
	@Column(name="value")
	@CollectionTable(name="exercise_templates", joinColumns=@JoinColumn(name="exerciseId"))
	private Map<String,String> codeTemplates;
}
