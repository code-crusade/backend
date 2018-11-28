package com.etsmtl.codecrusade.runner;

public interface RunnerResult {

    enum Status {
        ERROR,
        FAIL,
        SUCCESS
    }

    long getExecutionTime();

    String getOutput();

    Status getStatus();

    default boolean isError() {
        return Status.ERROR.equals(this.getStatus());
    }

    default boolean isFail() {
        return Status.FAIL.equals(this.getStatus());
    }

    default boolean isSuccess() {
        return Status.SUCCESS.equals(this.getStatus());
    }
}
