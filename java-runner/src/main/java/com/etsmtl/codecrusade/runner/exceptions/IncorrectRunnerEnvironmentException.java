package com.etsmtl.codecrusade.runner.exceptions;

public class IncorrectRunnerEnvironmentException extends RunnerException {
    public IncorrectRunnerEnvironmentException(Throwable cause) {
        super("Cannot start code runner, check environment config.", cause);
    }
}
