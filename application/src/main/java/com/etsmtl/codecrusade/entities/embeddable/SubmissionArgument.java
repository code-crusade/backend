package com.etsmtl.codecrusade.entities.embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class SubmissionArgument {
	@Column(name = "code")
	private String code;
	@Column(name = "language")
	private String language;
}
