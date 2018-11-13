package com.etsmtl.codecrusade.entities.embeddable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A composed id class for TemplateCode entities.
 */
@Embeddable
@Data
public class TemplateCodeId {

	@Column(name = "exerciseId")
	@Setter(AccessLevel.NONE)
	private Integer templateId;

	@Column(name = "lang")
	@Setter(AccessLevel.NONE)
	private String lang;
}
