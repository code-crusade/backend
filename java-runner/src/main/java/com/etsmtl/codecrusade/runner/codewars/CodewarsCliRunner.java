package com.etsmtl.codecrusade.runner.codewars;

import com.etsmtl.codecrusade.runner.Runner;
import com.etsmtl.codecrusade.runner.RunnerResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CodewarsCliRunner implements Runner {

    @Override
    public RunnerResult run(Language lang, String code, String test) {
        return this.run(CodewarsLanguage.fromRunnerLanguage(lang), 0, code, test);
    }

    @Override
    public RunnerResult run(Language lang, long timeout, String code, String test) {
        return this.run(CodewarsLanguage.fromRunnerLanguage(lang), timeout, code, test);
    }

    // TODO: Manage timeout.
    // TODO: Manage multiple tests?
    private CodewarsRunnerResult run(CodewarsLanguage lang, long timeout, String code, String test) {
        Runtime rt = Runtime.getRuntime();

        try {
            Process p = rt.exec(String.format("docker run --rm %s run -l %s -c \"%s\" -t %s -f \"%s\"",
                    lang.getContainerName(), lang.getRunnerName(), code, lang.getTestFormat(), test));

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            List<String> results = new ArrayList<>();

            while ((line = input.readLine()) != null) {
                results.add(line);
            }
            return new CodewarsRunnerResult(results, 0);

        } catch (IOException e) {
            return new CodewarsRunnerResult("Error: Failed to run cli. " + e, -1, RunnerResult.Status.ERROR);
        }
    }
}
