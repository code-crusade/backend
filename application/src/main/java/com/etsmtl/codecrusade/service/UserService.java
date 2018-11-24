package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.security.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Interface covering all user-related operations.
 */
public interface UserService {

    String ROLE_USER = "ROLE_USER";
    String ROLE_STUDENT = "ROLE_STUDENT";

	Iterable<User> findAll();

	Optional<User> createUser(@NotNull User user);

	Optional<User> findById(@NotNull Integer id);

	class UserNameExistsException extends RuntimeException {

    }
}
