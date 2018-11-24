package com.etsmtl.codecrusade.entities;

import lombok.Data;

/**
 * Model of an argument on a test assertion.
 */
@Data
public class TestArgument {
	private Object                   value;
	private ApplicationSupportedType type;
}
