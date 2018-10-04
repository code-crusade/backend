package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
