package com.etsmtl.codecrusade.annotation;

/**
 * Indicates an interpolated String key for a message to be used to .
 */
public @interface MessageTemplate {
	/**
	 * The message to be interpolated using {@link org.apache.commons.text.StringSubstitutor}.
	 *
	 * @return
	 */
	String value();
}
