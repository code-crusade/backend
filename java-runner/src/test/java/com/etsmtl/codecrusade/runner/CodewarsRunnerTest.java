package com.etsmtl.codecrusade.runner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Optional;

import static com.etsmtl.codecrusade.runner.ExecutionResult.ExecutionStatus.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;

public class CodewarsRunnerTest {
	private void passOrFail(String runnerOutput) {
		Assert.assertTrue("Output must at least contain \"<PASSED::>\" or \"<FAILED::>\"",
				runnerOutput.contains("<FAILED::>") || runnerOutput.contains("<PASSED::>"));
	}

	@Test
	public void testRunJavascript() {
		String code = "var a = 1;";
		String test = "Test.assertEquals(a, 1)";


		String result = CodewarsRunner.runCode(CodewarsRunner.KnownLanguage.JAVASCRIPT, Duration.ofSeconds(10), code, test);

		System.out.println("Output:" + result);
		passOrFail(result);
	}

	@Test
	public void testJavascriptCli() {
		String code = "var a = 1;";
		String test = "Test.assertEquals(a, 1)";

		String result = CodewarsRunner.runCode(CodewarsRunner.KnownLanguage.JAVASCRIPT, code, test);

		System.out.println("Output:" + result);
		passOrFail(result);
	}


	@Test
	public void testRunJava() {
		String code = 	"public class Foo {" +
						"    public int add(int a, int b){" +
						"        return a + b; " +
						"    }"+
						"}";

		String result = CodewarsRunner.runCode(CodewarsRunner.KnownLanguage.JAVA, Duration.ofSeconds(10), code, Optional.empty());
		Assert.assertFalse(result, result.contains("exec failed"));

		System.out.print(result);
	}

}
