package com.etsmtl.codecrusade.entities.embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class SubmissionArgument implements Serializable {
	@Column(name = "code")
	private String code;
	@Column(name = "language")
	private String language;
	@Column(name = "fixture")
    private String fixture;
}
