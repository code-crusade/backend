package com.etsmtl.codecrusade.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model of an argument on a test assertion.
 */
@Data
@NoArgsConstructor
public class TestArgument {
	private Object                   value;
	private ApplicationSupportedType type;
}
