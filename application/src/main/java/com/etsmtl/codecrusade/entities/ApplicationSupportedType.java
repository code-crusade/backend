package com.etsmtl.codecrusade.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ApplicationSupportedType {
	BOOLEAN("BOOLEAN"),

	INT("INT"),

	FLOAT("FLOAT"),

	STRING("STRING"),

	CHAR("CHAR"),

	BOOLEAN_ARRAY("BOOLEAN_ARRAY"),

	INT_ARRAY("INT_ARRAY"),

	FLOAT_ARRAY("FLOAT_ARRAY"),

	STRING_ARRAY("STRING_ARRAY"),

	CHAR_ARRAY("CHAR_ARRAY");

	@Getter
	private String value;

	ApplicationSupportedType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ApplicationSupportedType fromValue(String text) {
		for (ApplicationSupportedType b : ApplicationSupportedType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + text + "'");
	}
}
