package com.etsmtl.codecrusade.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ApplicationSupportedType {
	BOOLEAN("boolean"),

	INT("int"),

	FLOAT("float"),

	STRING("string"),

	BOOLEAN_("boolean[]"),

	INT_("int[]"),

	FLOAT_("float[]"),

	STRING_("string[]");

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
