package com.etsmtl.codecrusade.runner.codewars;

import com.etsmtl.codecrusade.runner.Runner;

enum CodewarsLanguage {

    C("c", "codewars/systems-runner", "criterion"),
    CPLUSPLUS("cpp", "codewars/systems-runner", "igloo"),
    CSHARP("csharp", "codewars/dotnet-runner", "nunit"),
    JAVA("java", "codewars/java-runner", "junit"),
    JAVASCRIPT("javascript", "codewars/node-runner", "cw"),
    PYTHON3("python3", "codewars/python-runner", "cw"),
    ;

    private final String runnerName;
    private final String containerName;
    private final String testFormat;

    CodewarsLanguage(String runnerName, String containerName, String testFormat) {
        this.runnerName = runnerName;
        this.containerName = containerName;
        this.testFormat = testFormat;
    }

    static CodewarsLanguage fromRunnerLanguage(Runner.Language lang) {
        CodewarsLanguage codewarsLanguage = null;

        for (CodewarsLanguage l : CodewarsLanguage.values()) {
            if (l.toString().equals(lang.toString())) {
                codewarsLanguage = l;
            }
        }
        return codewarsLanguage;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public String getContainerName() {
        return containerName;
    }

    public String getTestFormat() {
        return testFormat;
    }
}
