package com.etsmtl.codecrusade.entities;

import lombok.Getter;

import java.util.List;

/**
 * An enumeration of difficulties.
 */
public enum Difficulty {

	EASY("EASY"), MEDIUM("MEDIUM"), HARD("HARD");

	@Getter
	private final String value;

	Difficulty(String value) {
		this.value = value;
	}

	public static Difficulty from(String value) {
		return List.of(Difficulty.values())
				   .stream()
				   .filter(difficulty -> difficulty.value.equals(value))
				   .findFirst()
				   .orElse(null);
	}
}
