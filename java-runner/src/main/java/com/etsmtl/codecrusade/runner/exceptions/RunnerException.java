package com.etsmtl.codecrusade.runner.exceptions;

public abstract class RunnerException extends RuntimeException {
    public RunnerException (String message, Throwable cause) {
        super(message, cause);
    }

    public RunnerException (String message) {
        super(message);
    }

    public RunnerException () {
        super();
    }
}
