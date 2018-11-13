package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.MessageId;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@Data
public class Message {
	@EmbeddedId
	private MessageId id;

	private String    message;
}
