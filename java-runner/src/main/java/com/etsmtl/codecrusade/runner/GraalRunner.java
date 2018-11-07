package com.etsmtl.codecrusade.runner;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Language;

public class GraalRunner implements AutoCloseable {
    Context executionContext;

    private static Context createContext() throws IllegalArgumentException  {
        // "if no languages are provided then the use of all languages will be permitted"
        return Context.newBuilder()
                // All of these are false by default.
                .allowNativeAccess(false)
                .allowCreateThread(false)
                .allowHostClassLoading(false)
                .allowIO(false)
                .allowHostAccess(false)
                .build();
    }

    // Initialized with exercise parameters
    public GraalRunner () {
        executionContext = createContext();
        executionContext.getEngine().getLanguages();
        // [native, regex, nfi, llvm, js, ruby, r, etc.]
    }

    @Override public void close() {
        executionContext.close();
    }
}
