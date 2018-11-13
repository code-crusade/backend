package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.TemplateCodeId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "templateCode")
@Data
public class TemplateCode {

	@EmbeddedId
	private TemplateCodeId id;

	@MapsId("lang")
	private String lang;

	@MapsId("templateId")
	@ManyToOne
	private Template template;

	@Column(name="prependedCode")
	private String prependedCode;

	@Column(name="appendedCode")
	private String appendedCode;
}
