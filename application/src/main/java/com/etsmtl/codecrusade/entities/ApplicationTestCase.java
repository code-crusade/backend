package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "testCase")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApplicationTestCase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Integer id;

	@Version
	@Setter(AccessLevel.NONE)
	private Long version;

	@Column(name = "test_it")
	@Size(max = 50)
	private String it;

	@OneToMany
	@JoinColumn(name = "testCase_id",
				foreignKey = @ForeignKey(name = "fk_testCase_id"))
	private List<TestAssertion> assertions;
}
