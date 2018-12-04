package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.security.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("user")
public class Student extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;
}
