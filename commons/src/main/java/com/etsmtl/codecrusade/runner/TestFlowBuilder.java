package com.etsmtl.codecrusade.runner;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class TestFlowBuilder {

    public static final Pattern NEWLINE = Pattern.compile("([\n]|(\r\n))+");

    public static List<TestFlow> fromOutput(String output) {
        List<TestFlow> flows;
        if (StringUtils.isNotBlank(output)) {
            try (Scanner scanner = new Scanner(output).useDelimiter(NEWLINE)) {
                Spliterator<String> splt = Spliterators.spliterator(scanner, Long.MAX_VALUE, Spliterator.ORDERED | Spliterator.NONNULL);
                flows = StreamSupport.stream(splt, false)
                                     .onClose(scanner::close).map(line -> {
                            Matcher matcher = Describe.PATTERN.matcher(line);
                            if (matcher.matches()) {
                                return new Describe(matcher.group(2));
                            }
                            matcher = It.PATTERN.matcher(line);
                            if (matcher.matches()) {
                                return new It(matcher.group(2));
                            }
                            matcher = Passed.PATTERN.matcher(line);
                            if (matcher.matches()) {
                                return new Passed(matcher.group(2));
                            }
                            matcher = Failed.PATTERN.matcher(line);
                            if (matcher.matches()) {
                                return new Failed(matcher.group(2));
                            }
                            matcher = CompletedIn.PATTERN.matcher(line);
                            if (matcher.matches()) {
                                return new CompletedIn(matcher.group(2));
                            }
                            return new StdOut(line);
                        }).collect(toList());
            }
        } else {
            flows = emptyList();
        }
        return flows;
    }

    public interface TestFlow {
        String getMessage();
    }

    @Data
    @AllArgsConstructor
    public static class Describe implements TestFlow {
        private String message;
        public static final Pattern PATTERN = Pattern.compile("(<DESCRIBE::>)(.*)");
    }

    @Data
    @AllArgsConstructor
    public static class It implements TestFlow {
        private String message;
        public static final Pattern PATTERN = Pattern.compile("(<IT::>)(.*)");
    }

    @Data
    @AllArgsConstructor
    public static class Passed implements TestFlow {
        private String message;
        public static final Pattern PATTERN = Pattern.compile("(<PASSED::>)(.*)");
    }

    @Data
    @AllArgsConstructor
    public static class Failed implements TestFlow {
        private String message;
        public static final Pattern PATTERN = Pattern.compile("(<FAILED::>)(.*)");
    }

    @Data
    @AllArgsConstructor
    public static class CompletedIn implements TestFlow {
        private String message;
        public static final Pattern PATTERN = Pattern.compile("(<COMPLETEDIN::>)(.*)");
    }

    @Data
    @AllArgsConstructor
    public static class StdOut implements TestFlow {
        private String message;
    }
}
