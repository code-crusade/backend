package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.MessageId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@Data
public class Message {
	@Id
	public MessageId id;

	public String    message;
}
