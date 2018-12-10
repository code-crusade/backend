package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.security.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Interface covering all user-related operations.
 */
public interface UserService {

    String USER_GROUP = "USER";
    String ADMIN_GROUP = "ADMIN";

    Iterable<User> findAll();

    Optional<User> createUser(@NotNull User user);

    Optional<User> findById(@NotNull Integer id);

    /**
     * Exception that indicates an email was already found for a provided email.
     */
    class EmailExistsException extends RuntimeException {

    }

    class UserCreationException extends RuntimeException{

    }
}
