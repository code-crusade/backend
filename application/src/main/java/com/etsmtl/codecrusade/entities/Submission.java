package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.security.Auditable;
import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.entities.validation.SubmissionArgumentsValid;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "submission")
@Data
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@SubmissionArgumentsValid
@NoArgsConstructor
public class Submission extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
	private Integer id;

	@Column(name="code")
	@NotNull
	public String code;

	@Column(name="language")
	@NotNull
	public String language;

	@ManyToOne
	@JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "fk_exercise_id"))
	@NotNull
	private Exercise exercise;

	// TODO : somehow fill this automatically
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
	@NotNull
	private User user;
}
