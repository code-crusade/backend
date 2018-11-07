package com.etsmtl.codecrusade.entities.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Converter
public class ObjectListAttributeConverter implements AttributeConverter<List<Object>, String> {

	private static final TypeReference<List<Object>> TypeRef = new TypeReference<>() {
	};

	private ObjectMapper objectMapper;

	public ObjectListAttributeConverter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public String convertToDatabaseColumn(List<Object> attribute) {
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
	public List<Object> convertToEntityAttribute(String dbData) {
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