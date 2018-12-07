package com.etsmtl.codecrusade.runner;

import com.etsmtl.codecrusade.runner.codewars.CodewarsCliRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CodewarsRunnerTest {

    private Runner runner;

    @Before
    public void init(){
        runner = new CodewarsCliRunner();
    }

	private void passOrFail(String runnerOutput) {
		Assert.assertTrue("Output must at least contain \"<PASSED::>\" or \"<FAILED::>\"",
				runnerOutput.contains("<FAILED::>") || runnerOutput.contains("<PASSED::>"));
	}

	@Test
	public void testJavascriptCli() {
		String code = "var a = 1;";
		String test = "Test.assertEquals(a, 1)";

        RunnerResult runnerResult = runner.run(Runner.Language.JAVASCRIPT, code, test);

		passOrFail(runnerResult.getOutput());
	}


	@Test
	public void testRunJavaCli() {
		String code = 	"public class Foo {" +
						"    public String add(long a, long b){" +
						"        return \"test\"; " +
						"    }"+
						"}";
		String fixture =    "import org.junit.Test;" +
                            "import static org.junit.Assert.assertEquals;" +
                            "public class FooTest {" +
                                "@Test public void testFoo(){ " +
                                    "Foo foo = new Foo();" +
                                    "String added = foo.add(1L,2L);" +
                                    "assertEquals(\"test\",added);" +
                                "}" +
                            "}";

        RunnerResult runnerResult = runner.run(Runner.Language.JAVA, code, fixture);

        passOrFail(runnerResult.getOutput());
    }

}
