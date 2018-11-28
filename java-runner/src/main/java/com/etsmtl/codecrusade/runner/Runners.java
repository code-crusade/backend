package com.etsmtl.codecrusade.runner;

import com.etsmtl.codecrusade.runner.codewars.CodewarsCliRunner;

public final class Runners {

    private Runners() {}

    public static Runner getDefaultRunner() {
        return new CodewarsCliRunner();
    }
}
