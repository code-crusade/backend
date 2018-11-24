package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.security.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	User findByUsername(String username);
}
