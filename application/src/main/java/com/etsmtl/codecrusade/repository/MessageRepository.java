package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Message;
import com.etsmtl.codecrusade.entities.embeddable.MessageId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, MessageId> {
}
