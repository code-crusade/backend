package com.etsmtl.codecrusade.runner;

import javax.validation.constraints.NotNull;

/**
 * A runner runs language-specific code.
 */
@FunctionalInterface
public interface Runner {
	ExecutionResult evaluate(String rawCode, @NotNull RunnerArguments runnerArguments);
}
