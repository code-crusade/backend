package com.etsmtl.codecrusade.runner;

import com.etsmtl.codecrusade.runner.codewars.CodewarsCliRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CodewarsCliRunnerTest {

    private Runner runner;

    @Before
    public void init(){
        runner = new CodewarsCliRunner();
    }

	private void passOrFail(String runnerOutput) {
		Assert.assertTrue("Output must at least contain \"<PASSED::>\" or \"<FAILED::>\"",
				runnerOutput.contains("<FAILED::>") || runnerOutput.contains("<PASSED::>"));
	}

	private static void success(RunnerResult result) {
    	Assert.assertEquals(result.getStatus(), RunnerResult.Status.SUCCESS);
	}

	private static void failed(RunnerResult result) {
		Assert.assertEquals(result.getStatus(), RunnerResult.Status.FAILED);
	}

	private static void error(RunnerResult result) {
		Assert.assertEquals(result.getStatus(), RunnerResult.Status.ERROR);
	}

	private static void timeout(RunnerResult result) {
		Assert.assertEquals(result.getStatus(), RunnerResult.Status.TIMEOUT);
	}

	// TODO: C TEST: Success, Fail, Timeout, Compile error.
	// TODO: C++ TEST: Success, Fail, Timeout, Compile error.
	// TODO: C# TEST: Fail, Timeout, Compile error.
	// TODO: PYTHON TEST: Success, Fail, Timeout, Compile error.

	@Test
	public void testCSharp_Success() {
    	String code = "public class Foo {"
				+ "  public string foo() "
				+ "  {"
				+ "    return \"test\";"
				+ "  }"
				+ "}";
    	String test =  "using NUnit.Framework;"
				+ " [TestFixture]"
				+ " public class FooTest"
				+ " {"
				+ "   [Test]"
				+ "   public void testFoo()"
				+ "   {"
				+ "     Foo foo = new Foo();"
				+ "     string result = foo.foo();"
				+ "     Assert.That(result, Is.EqualTo(\"test\"));"
				+ "   }"
				+ " }";
    	success(this.runner.run(Runner.Language.CSHARP, code, test));
	}

	// Java Tests.
	@Test
	public void testJava_Error() {
		String code = 	"public class Foo {" +
				"    public String add () {" +
				"        return \"test\";" +
				"    }"+
				"}";
		String test =    "import org.junit.Test;" +
				"import static org.junit.Assert.assertEquals;" +
				"public class FooTest {" +
				"@Test public void testFoo() {" +
				"Foo foo = new Foo();" +
				"String added = foo.add(1L,2L);" +
				"assertEquals(\"test\",added);" +
				"}" +
				"}";

		error(this.runner.run(Runner.Language.JAVA, code, test));
	}

	@Test
	public void testJava_Failed() {
		String code = 	"public class Foo {" +
				"    public String add (long a, long b) {" +
				"        return \"failed\";" +
				"    }"+
				"}";
		String test =    "import org.junit.Test;" +
				"import static org.junit.Assert.assertEquals;" +
				"public class FooTest {" +
				"@Test public void testFoo() {" +
				"Foo foo = new Foo();" +
				"String added = foo.add(1L,2L);" +
				"assertEquals(\"test\",added);" +
				"}" +
				"}";

		failed(runner.run(Runner.Language.JAVA, code, test));
	}

	@Test
	public void testJava_Success() {
		String code = 	"public class Foo {" +
				"    public String add (long a, long b) {" +
				"        return \"test\";" +
				"    }"+
				"}";
		String test =    "import org.junit.Test;" +
				"import static org.junit.Assert.assertEquals;" +
				"public class FooTest {" +
				"@Test public void testFoo() {" +
				"Foo foo = new Foo();" +
				"String added = foo.add(1L,2L);" +
				"assertEquals(\"test\",added);" +
				"}" +
				"}";

		success(this.runner.run(Runner.Language.JAVA, code, test));
	}

	@Test
	public void testJava_Timeout() {
		String code = 	"public class Foo {" +
				"    public String add (long a, long b) {" +
				"        while(true);" +
				"        return \"test\";" +
				"    }"+
				"}";
		String test =    "import org.junit.Test;" +
				"import static org.junit.Assert.assertEquals;" +
				"public class FooTest {" +
				"@Test public void testFoo() {" +
				"Foo foo = new Foo();" +
				"String added = foo.add(1L,2L);" +
				"assertEquals(\"test\",added);" +
				"}" +
				"}";

		timeout(runner.run(Runner.Language.JAVA, 30, code, test));
	}

	// JavaScript Tests.
	@Test
	public void testJavascript_Error() {
    	String code = "--";
    	String test = "Test.assertEquals(a, 'a')";

    	error(this.runner.run(Runner.Language.JAVASCRIPT, code, test));
	}

	@Test
	public void testJavaScript_Failed() {
		String code = "var a = 'c'";
		String test = "Test.assertEquals(a, 'a')";

		failed(this.runner.run(Runner.Language.JAVASCRIPT, code, test));
	}

	@Test
	public void testJavaScript_Success() {
		String code = "var a = 'a'";
		String test = "Test.assertEquals(a, 'a')";

		success(this.runner.run(Runner.Language.JAVASCRIPT, code, test));
	}

	@Test
	public void testJavascript_Timeout() {
		String code = "for(;true;)";
		String test = "Test.assertEquals(a, 'a')";

		timeout(this.runner.run(Runner.Language.JAVASCRIPT, 30, code, test));
	}
}
