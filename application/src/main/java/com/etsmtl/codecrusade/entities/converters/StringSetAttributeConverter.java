package com.etsmtl.codecrusade.entities.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Set;

@Converter
public class StringSetAttributeConverter implements AttributeConverter<Set<String>, String> {

    private static final TypeReference<Set<String>> TypeRef = new TypeReference<>() {
    };

    @Autowired
    private ObjectMapper objectMapper;

    public StringSetAttributeConverter() {
    }

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
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
    public Set<String> convertToEntityAttribute(String dbData) {
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

