package com.etsmtl.codecrusade.runner.exceptions;

public class AbnormalRunnerExitException extends RunnerException {
    public AbnormalRunnerExitException(Throwable cause) {
        super("Runner was terminated abnormally.", cause);
    }
}
