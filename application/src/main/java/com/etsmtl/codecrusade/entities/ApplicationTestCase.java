package com.etsmtl.codecrusade.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
	private String it;

	@OneToMany
	@JoinColumn(name = "testCase_id",
				foreignKey = @ForeignKey(name = "fk_testCase_id"))
	private List<TestAssertion> assertions;
}
