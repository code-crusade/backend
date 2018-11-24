package com.etsmtl.codecrusade.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Semester {

	FALL("FALL"),

	WINTER("WINTER"),

	SUMMER("SUMMER");

	@Getter
	private String value;

	Semester(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static Semester fromValue(String text) {
		for (Semester b : Semester.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + text + "'");
	}
}
