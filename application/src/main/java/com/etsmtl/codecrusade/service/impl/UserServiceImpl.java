package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.repository.UserRepository;
import com.etsmtl.codecrusade.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Service implementation for user-based operations.
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> createUser(@NotNull User user) {
		User saved = userRepository.save(user);
		if (saved.getId() != null) {
			return Optional.of(user);
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> findById(@NotNull Integer id) {
		return userRepository.findById(id);
	}
}
