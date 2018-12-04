package com.etsmtl.codecrusade.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "testCase")
@Data
@NoArgsConstructor
public class ApplicationTestCase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "test_it")
	@Size(max = 50)
	private String it;

	@OneToMany
	@JoinColumn(name = "testCase_id",
				foreignKey = @ForeignKey(name = "fk_testCase_id"))
	private List<TestAssertion> assertions;
}
