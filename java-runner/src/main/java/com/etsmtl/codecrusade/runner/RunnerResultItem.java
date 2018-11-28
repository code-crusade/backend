package com.etsmtl.codecrusade.runner;

public interface RunnerResultItem {

    String getOutput();

    RunnerResult.Status getStatus();

    default boolean isError() {
        return RunnerResult.Status.ERROR.equals(this.getStatus());
    }

    default boolean isFail() {
        return RunnerResult.Status.FAIL.equals(this.getStatus());
    }

    default boolean isSuccess() {
        return RunnerResult.Status.SUCCESS.equals(this.getStatus());
    }
}
