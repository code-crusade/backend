package com.etsmtl.codecrusade.runner;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestFlowBuilderTest {

    @Test
    public void parsesDescribe() {
        List<TestFlowBuilder.TestFlow> flows = TestFlowBuilder.fromOutput("<DESCRIBE::>Should be parsed");
        assertThat(flows).isNotNull();
        assertThat(flows.size()).isEqualTo(1);
        assertThat(flows.get(0)).isInstanceOf(TestFlowBuilder.Describe.class);
        assertThat(flows.get(0).getMessage()).isEqualTo("Should be parsed");
    }

    @Test
    public void parsesIt() {
        List<TestFlowBuilder.TestFlow> flows = TestFlowBuilder.fromOutput("<IT::>Should be parsed");
        assertThat(flows).isNotNull();
        assertThat(flows.size()).isEqualTo(1);
        assertThat(flows.get(0)).isInstanceOf(TestFlowBuilder.It.class);
        assertThat(flows.get(0).getMessage()).isEqualTo("Should be parsed");
    }

    @Test
    public void parsesPassed() {
        List<TestFlowBuilder.TestFlow> flows = TestFlowBuilder.fromOutput("<PASSED::>Should be parsed");
        assertThat(flows).isNotNull();
        assertThat(flows.size()).isEqualTo(1);
        assertThat(flows.get(0)).isInstanceOf(TestFlowBuilder.Passed.class);
        assertThat(flows.get(0).getMessage()).isEqualTo("Should be parsed");
    }

    @Test
    public void parsesFailed() {
        List<TestFlowBuilder.TestFlow> flows = TestFlowBuilder.fromOutput("<FAILED::>Should be parsed");
        assertThat(flows).as("foo bar").isNotNull();
        assertThat(flows.size()).isEqualTo(1);
        assertThat(flows.get(0)).isInstanceOf(TestFlowBuilder.Failed.class);
        assertThat(flows.get(0).getMessage()).isEqualTo("Should be parsed");
    }

    @Test
    public void parsesCompletedIn() {
        List<TestFlowBuilder.TestFlow> flows = TestFlowBuilder.fromOutput("<COMPLETEDIN::>Should be parsed");
        assertThat(flows).isNotNull();
        assertThat(flows.size()).isEqualTo(1);
        assertThat(flows.get(0)).isInstanceOf(TestFlowBuilder.CompletedIn.class);
        assertThat(flows.get(0).getMessage()).isEqualTo("Should be parsed");
    }

    @Test
    public void parsesStdout() {
        List<TestFlowBuilder.TestFlow> flows = TestFlowBuilder.fromOutput("Should be parsed");
        assertThat(flows).isNotNull();
        assertThat(flows.size()).isEqualTo(1);
        assertThat(flows.get(0)).isInstanceOf(TestFlowBuilder.StdOut.class);
        assertThat(flows.get(0).getMessage()).isEqualTo("Should be parsed");
    }

    @Test
    public void parsesSuite() {
        List<TestFlowBuilder.TestFlow> flows = TestFlowBuilder.fromOutput("<DESCRIBE::>Should be parsed\n<IT::>Should succeed\nfoo\nbar\n<PASSED::>Has succeeded\n<COMPLETEDIN::>24\n<COMPLETEDIN::>30");
        assertThat(flows).isNotNull();
        assertThat(flows.size()).isEqualTo(7);
        assertThat(flows.get(0)).isInstanceOf(TestFlowBuilder.Describe.class);
        assertThat(flows.get(1)).isInstanceOf(TestFlowBuilder.It.class);
        assertThat(flows.get(2)).isInstanceOf(TestFlowBuilder.StdOut.class);
        assertThat(flows.get(3)).isInstanceOf(TestFlowBuilder.StdOut.class);
        assertThat(flows.get(4)).isInstanceOf(TestFlowBuilder.Passed.class);
        assertThat(flows.get(5)).isInstanceOf(TestFlowBuilder.CompletedIn.class);
        assertThat(flows.get(6)).isInstanceOf(TestFlowBuilder.CompletedIn.class);
        assertThat(flows.get(0).getMessage()).isEqualTo("Should be parsed");
        assertThat(flows.get(1).getMessage()).isEqualTo("Should succeed");
        assertThat(flows.get(2).getMessage()).isEqualTo("foo");
        assertThat(flows.get(3).getMessage()).isEqualTo("bar");
        assertThat(flows.get(4).getMessage()).isEqualTo("Has succeeded");
        assertThat(flows.get(5).getMessage()).isEqualTo("24");
        assertThat(flows.get(6).getMessage()).isEqualTo("30");

    }

}
