package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "entryPoints")
@NoArgsConstructor
public class EntryPoint {

	@Id
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
	private Integer id;

	@Version
	@Setter(AccessLevel.NONE)
	private Long version;

	@Column(name = "functionName")
	@Size(max=50)
	private String functionName;

	@OneToMany
	@JoinColumn(name = "entryPoint_id",
				foreignKey = @ForeignKey(name = "fk_entryPoint_id"))
	private List<FunctionParams> functionParams = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private ApplicationSupportedType returnType;

	@Builder
    public EntryPoint(@Size(max = 50) String functionName, List<FunctionParams> functionParams, ApplicationSupportedType returnType) {
        this.id = id;
        this.version = version;
        this.functionName = functionName;
        this.functionParams = functionParams;
        this.returnType = returnType;
    }
}
