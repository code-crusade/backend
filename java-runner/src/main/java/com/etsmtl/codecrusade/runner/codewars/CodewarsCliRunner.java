package com.etsmtl.codecrusade.runner.codewars;

import com.etsmtl.codecrusade.runner.Runner;
import com.etsmtl.codecrusade.runner.RunnerResult;
import org.apache.commons.exec.*;
import org.apache.commons.lang3.time.StopWatch;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

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
                .addArgument(String.format("\"%s\"", code.replaceAll("\"", "\\\\\"").replaceAll("\n", "\\\\n")), false)
                .addArgument("-t")
                .addArgument(lang.getTestFormat())
                .addArgument("-f")
                .addArgument(String.format("\"%s\"", test.replaceAll("\"", "\\\\\"").replaceAll("\n", "\\\\n")), false);

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
                result = new CodewarsRunnerResult(out.toString().replaceAll("<:LF:>", "\n"), watch.getTime(), RunnerResult.Status.ERROR);
            }
        } catch (ExecuteException e) {
            if (e.getExitValue() == 143 || e.getExitValue() == 1) {
                result = new CodewarsRunnerResult(e.toString(), timeout, RunnerResult.Status.TIMEOUT, e.getExitValue());
            } else {
                result = new CodewarsRunnerResult(e.toString(), timeout, RunnerResult.Status.ERROR, e.getExitValue());
            }
        } catch (IOException e) {
            result = new CodewarsRunnerResult(e.toString(), timeout, RunnerResult.Status.ERROR, -1);
        }
        return result;
    }
}
