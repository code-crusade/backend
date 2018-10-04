package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.security.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("STUDENT")
public class Student extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
