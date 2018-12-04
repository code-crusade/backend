package com.etsmtl.codecrusade.runner.codewars;

import com.etsmtl.codecrusade.runner.Runner;
import com.etsmtl.codecrusade.runner.RunnerResult;
import org.apache.commons.exec.*;
import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodewarsCliRunner implements Runner {

    @Override
    public RunnerResult run(Language lang, String code, String test) {
        return this.runSafe(CodewarsLanguage.fromRunnerLanguage(lang), ExecuteWatchdog.INFINITE_TIMEOUT, code, test);
    }

    @Override
    public RunnerResult run(Language lang, long timeout, String code, String test) {
        return this.runSafe(CodewarsLanguage.fromRunnerLanguage(lang), timeout, code, test);
    }

    private CodewarsRunnerResult runSafe(CodewarsLanguage lang, long timeout, String code, String test) {
        CodewarsRunnerResult result;

        CommandLine cmd = new CommandLine("docker")
                .addArgument("run")
                .addArgument(lang.getContainerName())
                .addArgument("run")
                .addArgument("-l")
                .addArgument(lang.getRunnerName())
                .addArgument("-c")
                .addArgument(String.format("\"%s\"", code.replaceAll("\"", "\\\"")), false)
                .addArgument("-t")
                .addArgument(lang.getTestFormat())
                .addArgument("-f")
                .addArgument(String.format("\"%s\"", test.replaceAll("\"", "\\\"")), false);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setWatchdog(new ExecuteWatchdog(timeout));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PumpStreamHandler streamHandler = new PumpStreamHandler(out);
        executor.setStreamHandler(streamHandler);

        StopWatch watch = StopWatch.createStarted();

        try {
            if (executor.execute(cmd) == 0) {
                watch.stop();
                result = new CodewarsRunnerResult(Arrays.asList(out.toString().split("\n")), watch.getTime());

            } else {
                watch.stop();
                result = new CodewarsRunnerResult(out.toString(), watch.getTime(), RunnerResult.Status.ERROR);
            }
        } catch (IOException e) {
            watch.stop();
            result = new CodewarsRunnerResult(e.toString(), watch.getTime(), RunnerResult.Status.ERROR);
        }

        return result;
    }

    @Deprecated
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
