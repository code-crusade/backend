package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.security.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
}
