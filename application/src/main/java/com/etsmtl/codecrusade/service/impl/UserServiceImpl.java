package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.repository.UserRepository;
import com.etsmtl.codecrusade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

/**
 * Service implementation for user-based operations.
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> createUser(@NotNull User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserNameExistsException();
        }
        /**
         * Guarantee that the user has ROLE_USER at least
         */
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<String> roles = user.getRoles();
        roles.add(UserService.ROLE_USER);
        user.setRoles(roles);
        User saved = userRepository.save(user);
        if (saved.getId() != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(@NotNull Integer id) {
        return userRepository.findById(id);
    }
}
