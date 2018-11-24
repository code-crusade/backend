package com.etsmtl.codecrusade.entities.converters;

import javax.persistence.AttributeConverter;
import java.util.Locale;

/**
 * Converts locales to and from a database string column.
 */
public class LocaleConverter implements AttributeConverter<Locale, String> {
	@Override
	public String convertToDatabaseColumn(Locale attribute) {
		return attribute.toLanguageTag();
	}

	@Override
	public Locale convertToEntityAttribute(String dbData) {
		return Locale.forLanguageTag(dbData);
	}
}
