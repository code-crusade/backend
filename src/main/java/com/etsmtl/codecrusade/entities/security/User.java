package com.etsmtl.codecrusade.entities.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorColumn(name = "USER_TYPE")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Email
	private String email;
	private String password;

	@ManyToMany
	@JoinTable(name = "users_roles",
			   joinColumns = @JoinColumn(name = "user_id",
										 referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(name = "role_id",
												referencedColumnName = "id"))
	private Collection<Role> roles;
}