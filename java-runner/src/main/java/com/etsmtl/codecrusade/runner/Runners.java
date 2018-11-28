package com.etsmtl.codecrusade.runner;

public final class Runners {

    private Runners() {}

    public static Runner getDefaultRunner() {
        return new CodewarsCliRunner();
    }
}
