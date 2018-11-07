package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.entities.security.User;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "submission")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Embedded
	private SubmissionArgument program;

	@ManyToOne
	@JoinColumn(name = "submission_id")
	private Exercise exercise;

	@ManyToOne
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private OffsetDateTime createdAt;
}
