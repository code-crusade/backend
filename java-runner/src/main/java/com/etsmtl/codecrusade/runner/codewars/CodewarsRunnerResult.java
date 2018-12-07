package com.etsmtl.codecrusade.runner.codewars;

import com.etsmtl.codecrusade.runner.RunnerResult;
import com.etsmtl.codecrusade.runner.RunnerResultItem;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CodewarsRunnerResult implements RunnerResult {

    private static final Pattern CODEWARS_OUTPUT_PATTERN;

    private long executionTime;
    private String output;
    private Status status;
    private List<RunnerResultItem> items;

    CodewarsRunnerResult(String output, long executionTime, Status status) {
        this.executionTime = executionTime;
        this.output = output;
        this.status = status;
    }

    CodewarsRunnerResult(List<String> output, long executionTime) {
        this.items = new LinkedList<>();
        this.output = String.join("\n", output);
        this.parseOutput(output);
        this.executionTime = executionTime;

        this.status = this.items.isEmpty() ? Status.ERROR : Status.SUCCESS;

        for (RunnerResultItem item : this.items) {
            if (item.isError()) {
                this.status = Status.ERROR;
                break;

            } else if (item.isFail() && this.isSuccess()) {
                this.status = Status.FAILED;
            }
        }
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

    @Override
    public List<RunnerResultItem> getItems() {
        return this.items;
    }

    private void parseOutput(List<String> output) {
        for (String o : output) {
            Matcher m = CODEWARS_OUTPUT_PATTERN.matcher(o);

            if (m.matches()) {
                items.add(new CodewarsRunnerResultItem(m.group(1), m.group(2)));
            }
        }
    }

    static {
        CODEWARS_OUTPUT_PATTERN = Pattern.compile("^<(PASSED|FAILED|ERROR)::>(.+)$");
    }
}
