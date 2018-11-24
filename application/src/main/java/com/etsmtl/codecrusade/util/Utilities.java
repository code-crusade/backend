package com.etsmtl.codecrusade.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * Utility class for various helper functions
 */
@UtilityClass
public class Utilities {

	/**
	 * Returns an optional wrapping given nullable value. This achieves nothing special and only serves for code to read
	 * better.
	 *
	 * @param nullable
	 * @param <T>
	 * @return
	 */
	public static <T> Optional<T> nullable(T nullable) {
		return Optional.ofNullable(nullable);
	}
}
