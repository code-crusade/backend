package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
