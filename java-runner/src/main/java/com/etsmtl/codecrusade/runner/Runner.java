package com.etsmtl.codecrusade.runner;

public interface Runner {

    enum Language {
        C,
        CPLUSPLUS,
        CSHARP,
        JAVA,
        JAVASCRIPT,
        PYTHON3,
    }

    RunnerResult run(Language lang, String code, String test);

    RunnerResult run(Language lang, long timeout, String code, String test);
}
