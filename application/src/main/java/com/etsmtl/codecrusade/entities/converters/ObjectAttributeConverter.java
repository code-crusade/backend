package com.etsmtl.codecrusade.entities.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Converts an object into a json string.
 */
@Converter
public class ObjectAttributeConverter implements AttributeConverter<Object, String> {

	private static final TypeReference<Object> TypeRef = new TypeReference<>() {
	};

	@Autowired
	private ObjectMapper objectMapper;

	public ObjectAttributeConverter() {
	}

	@Override
	public String convertToDatabaseColumn(Object attribute) {
		if (attribute == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	@Override
	public Object convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		try {
			return objectMapper.readValue(dbData, TypeRef);
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}
}
