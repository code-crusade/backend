package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.security.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
