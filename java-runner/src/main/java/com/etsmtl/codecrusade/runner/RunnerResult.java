package com.etsmtl.codecrusade.runner;

import java.util.List;

public interface RunnerResult {

    enum Status {
        ERROR,
        FAIL,
        SUCCESS
    }

    long getExecutionTime();

    String getOutput();

    Status getStatus();

    List<RunnerResultItem> getItems();

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
