package com.etsmtl.codecrusade.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "templates")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Template {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
	private Integer id;

	@Version
	@Setter(AccessLevel.NONE)
	private Long version;

	@OneToOne
	@JoinColumn(name = "entrypoint_id",
				foreignKey = @ForeignKey(name = "fk_entrypoint_id"))
	private EntryPoint entryPoint;

	@Column(name = "returnType")
	@Enumerated(EnumType.STRING)
	private ApplicationSupportedType functionReturnType;

	@OneToMany(mappedBy = "template")
	private List<TemplateCode> templateCode = new ArrayList<>();
    @Builder
    public Template(EntryPoint entryPoint, ApplicationSupportedType functionReturnType, List<TemplateCode> templateCode) {
        this.id = id;
        this.version = version;
        this.entryPoint = entryPoint;
        this.functionReturnType = functionReturnType;
        this.templateCode = templateCode;
    }
}
