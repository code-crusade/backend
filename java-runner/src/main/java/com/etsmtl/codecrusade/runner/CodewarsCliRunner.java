package com.etsmtl.codecrusade.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CodewarsCliRunner implements Runner {

    public enum KnownLanguage {
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

        KnownLanguage(String runnerName, String containerName, String testFormat) {
            this.runnerName = runnerName;
            this.containerName = containerName;
            this.testFormat = testFormat;
        }

        static KnownLanguage fromRunnerLanguage(Language lang) {
            KnownLanguage knownLanguage = null;

            for (KnownLanguage l : KnownLanguage.values()) {
                if (l.toString().equals(lang.toString())) {
                    knownLanguage = l;
                }
            }
            return knownLanguage;
        }
    }

    @Override
    public RunnerResult run(Language lang, String code, String test) {
        return this.run(KnownLanguage.fromRunnerLanguage(lang), 0, code, test);
    }

    @Override
    public RunnerResult run(Language lang, long timeout, String code, String test) {
        return this.run(KnownLanguage.fromRunnerLanguage(lang), timeout, code, test);
    }

    // TODO: Manage timeout.
    // TODO: Manage multiple tests?
    private CodewarsCliRunnerResult run(KnownLanguage lang, long timeout, String code, String test) {
        Runtime rt = Runtime.getRuntime();

        try {
            Process p = rt.exec(String.format("docker run --rm %s run -l %s -c \"%s\" -t %s -f \"%s\"",
                    lang.containerName, lang.runnerName, code, lang.testFormat, test));

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            List<String> results = new ArrayList<>();

            while ((line = input.readLine()) != null) {
                results.add(line);
            }
            return new CodewarsCliRunnerResult(results.stream().collect(Collectors.joining("\n")), 0 , RunnerResult.Status.SUCCESS);

        } catch (IOException e) {
            return new CodewarsCliRunnerResult("Error: Failed to run cli. " + e, -1, RunnerResult.Status.ERROR);
        }
    }

    private static class CodewarsCliRunnerResult implements RunnerResult {

        private long executionTime;
        private String output;
        private Status status;

        public CodewarsCliRunnerResult(String output, long executionTime, Status status) {
            this.executionTime = executionTime;
            this.output = output;
            this.status = status;
        }

        @Override
        public long getExecutionTime() {
            return this.executionTime;
        }

        @Override
        public String getOutput() {
            return this.output;
        }

        @Override
        public Status getStatus() {
            return this.status;
        }
    }
}
