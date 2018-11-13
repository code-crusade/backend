package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.converters.ObjectAttributeConverter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "templates")
@Data
public class Template {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "entrypoint_id",
				foreignKey = @ForeignKey(name = "fk_entrypoint_id"))
	private EntryPoint entryPoint;

	@Column(name = "functionReturnValue")
	@Convert(converter = ObjectAttributeConverter.class)
	private Object functionReturnValue;

	@Column(name = "returnType")
	@Enumerated(EnumType.STRING)
	private ApplicationSupportedType functionReturnType;

	@OneToMany(mappedBy = "template")
	private List<TemplateCode> templateCode = new ArrayList<>();
}
