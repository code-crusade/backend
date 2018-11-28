package com.etsmtl.codecrusade.runner.codewars;

import com.etsmtl.codecrusade.runner.RunnerResult;
import com.etsmtl.codecrusade.runner.RunnerResultItem;

class CodewarsRunnerResultItem implements RunnerResultItem {

    private String output;
    private RunnerResult.Status status;

    CodewarsRunnerResultItem(String status, String output) {
        try {
            this.status = RunnerResult.Status.valueOf(status);

        } catch (IllegalArgumentException ok) {
            this.status = RunnerResult.Status.SUCCESS;
        }
        this.output = output;
    }

    @Override
    public String getOutput() {
        return this.output;
    }

    @Override
    public RunnerResult.Status getStatus() {
        return this.status;
    }

}
