package com.etsmtl.codecrusade.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

	@Column(name = "functionName")
	@Size(max=50)
	private String functionName;

	@OneToMany
	@JoinColumn(name = "entryPoint_id",
				foreignKey = @ForeignKey(name = "fk_entryPoint_id"))
	private List<FunctionParams> functionParams = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private ApplicationSupportedType returnType;

}
