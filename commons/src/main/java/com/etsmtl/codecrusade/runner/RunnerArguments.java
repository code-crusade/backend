package com.etsmtl.codecrusade.runner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Contains the arguments for a runner execution.
 */
@Data
@AllArgsConstructor
@Builder
public class RunnerArguments {

	/**
	 * PascalCase regex.
	 */
	@Pattern(regexp = "^[A-Z][a-z]+(?:[A-Z][a-z]+)*$")
	@NotBlank
	private String className;

	/**
	 * CamelCase regex.
	 */
	@Pattern(regexp = "^[a-z]+(?:[A-Z][a-z]+)*$")
	@NotBlank
	private String methodName;


	private Class[] parameterTypes;

	private Object[] methodArguments;

	/**
	 * Null or empty means that
	 * <p>
	 * a void method with no exception will succeed
	 */
	private Object expectedResult;
}
