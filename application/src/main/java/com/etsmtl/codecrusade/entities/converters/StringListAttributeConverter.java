package com.etsmtl.codecrusade.entities.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Converter
public class StringListAttributeConverter implements AttributeConverter<List<String>, String> {

	private static final TypeReference<List<String>> TypeRef = new TypeReference<>() {
	};

	private ObjectMapper objectMapper;

	public StringListAttributeConverter(ObjectMapper objectMapper){
		this.objectMapper = objectMapper;
	}

	@Override
	public String convertToDatabaseColumn (List<String> attribute) {
		if (attribute == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(attribute);
		}
		catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	@Override
	public List<String> convertToEntityAttribute (String dbData) {
		if (dbData == null) {
			return null;
		}
		try {
			return objectMapper.readValue(dbData, TypeRef);
		}
		catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}
}
