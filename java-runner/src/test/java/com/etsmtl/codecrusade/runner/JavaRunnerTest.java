package com.etsmtl.codecrusade.runner;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

import static com.etsmtl.codecrusade.runner.ExecutionResult.ExecutionStatus.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;

public class JavaRunnerTest {

	Logger logger = Logger.getLogger(getClass());

	private RunnerArguments arguments;

	private Runner runner;

	private String code;

	@Before
	public void setup() {
		arguments = RunnerArguments.builder()
								   .methodArguments(new Object[] { 1, 1 })
								   .className("Foo")
								   .expectedResult(2)
								   .methodName("add")
								   .parameterTypes(new Class[] { int.class, int.class })
								   .build();
		code = 	"public class Foo {" +
				"    public int add(int a, int b){" +
				"        return a + b; " +
				"    }"+
				"}";
		runner = new JavaRunner();
	}

	@Test
	public void testHappyPath() {
		long startTime = System.currentTimeMillis();
		ExecutionResult result = runner.evaluate(code,arguments);
		long elapsed = (System.currentTimeMillis() - startTime);
		logger.info(String.format("Took %f seconds", elapsed/1000d));
		assertThat(result.getStatus()).isEqualTo(SUCCESS);
	}
}
