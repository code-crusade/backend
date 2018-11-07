package com.etsmtl.codecrusade.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "entryPoints")
public class EntryPoint {

	@Id
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "className")
	private String className;

	@Column(name = "functionName")
	private String functionName;

	@OneToMany
	@JoinColumn(name = "entryPoint_id",
				foreignKey = @ForeignKey(name = "fk_entryPoint_id"))
	private List<FunctionParams> functionParams;

	@Enumerated(EnumType.STRING)
	private ApplicationSupportedType returnType;

}
