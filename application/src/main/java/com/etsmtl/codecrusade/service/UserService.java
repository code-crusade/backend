package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.security.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Interface covering all user-related operations.
 */
public interface UserService {
	Iterable<User> findAll();

	Optional<User> createUser(@NotNull User user);

	Optional<User> findById(@NotNull Integer id);
}
