package com.etsmtl.codecrusade.runner.exceptions;

public class MissingLanguageSupportException extends RunnerException {
    String languageName;

    public MissingLanguageSupportException(String languageName, Throwable cause) {
        super("No support for \"" + languageName + "\" detected.", cause);
        this.languageName = languageName;
    }
}
