package com.etsmtl.codecrusade.entities.embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class SubmissionArgument implements Serializable {
	@Column(name = "code")
	@Size(max = 5000)
	private String code;

	@Column(name = "language")
	@Size(max = 255)
	private String language;

	@Column(name = "fixture")
	@Size(max = 5000)
    private String fixture;
}
