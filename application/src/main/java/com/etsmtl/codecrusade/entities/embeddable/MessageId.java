package com.etsmtl.codecrusade.entities.embeddable;

import com.etsmtl.codecrusade.entities.converters.LocaleConverter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Locale;

/**
 * A composed id class for messages.
 */
@Embeddable
@Data
@AllArgsConstructor
public class MessageId {
	@Column(name = "locale")
	@Convert(converter = LocaleConverter.class)
	private Locale locale;

	@Column(name = "key")
	@Null
	@Size(max = 255)
	private String key;
}
