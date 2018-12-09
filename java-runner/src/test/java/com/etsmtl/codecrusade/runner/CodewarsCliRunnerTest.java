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
	// TODO: PYTHON TEST: Success, Fail, Timeout, Compile error.

    // C++ Tests.
    @Test
    public void testCpp_Error() {
        String code = "class Foo { public: int foo() { return 1 }};";
        String test = "#include <igloo/igloo.h>\n" +
                "using namespace igloo;\n" +
                "\n" +
                "Context(foo_test)\n" +
                "{\n" +
                "  Spec(foo_test_1)\n" +
                "  {\n" +
                "    Assert::That(foo.foo(), Equals(1));\n" +
                "  }\n" +
                "\n" +
                "  Foo foo;\n" +
                "};\n";

        error(this.runner.run(Runner.Language.CPLUSPLUS, code, test));
    }

    @Test
    public void testCpp_Failed() {
        String code = "class Foo { public: int foo() { return 1; }};";
        String test = "#include <igloo/igloo.h>\n" +
                "using namespace igloo;\n" +
                "\n" +
                "Context(foo_test)\n" +
                "{\n" +
                "  Spec(foo_test_1)\n" +
                "  {\n" +
                "    Assert::That(foo.foo(), Equals(2));\n" +
                "  }\n" +
                "\n" +
                "  Foo foo;\n" +
                "};\n";

        failed(this.runner.run(Runner.Language.CPLUSPLUS, code, test));
    }

    @Test
    public void testCpp_Success() {
        String code = "class Foo { public: int foo() { return 1; }};";
        String test = "#include <igloo/igloo.h>\n" +
                "using namespace igloo;\n" +
                "\n" +
                "Context(foo_test)\n" +
                "{\n" +
                "  Spec(foo_test_1)\n" +
                "  {\n" +
                "    Assert::That(foo.foo(), Equals(1));\n" +
                "  }\n" +
                "\n" +
                "  Foo foo;\n" +
                "};\n";

        success(this.runner.run(Runner.Language.CPLUSPLUS, code, test));
    }

    @Test
    public void testCpp_Timeout() {
        String code = "class Foo { public: int foo() { while(true); return 1; }};";
        String test = "#include <igloo/igloo.h>\n" +
                "using namespace igloo;\n" +
                "\n" +
                "Context(foo_test)\n" +
                "{\n" +
                "  Spec(foo_test_1)\n" +
                "  {\n" +
                "    Assert::That(foo.foo(), Equals(1));\n" +
                "  }\n" +
                "\n" +
                "  Foo foo;\n" +
                "};\n";

        timeout(this.runner.run(Runner.Language.CPLUSPLUS, 60000, code, test));
    }

	// C# Tests.
	@Test
	public void testCSharp_Error() {
		String code = "public class Foo {"
				+ "  public string foo() "
				+ "  {"
				+ "    return \"test\""
				+ "  }"
				+ "}";
		String test = "using NUnit.Framework;"
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
		error(this.runner.run(Runner.Language.CSHARP, code, test));
	}

	@Test
	public void testCSharp_Failed() {
		String code = "public class Foo {"
				+ "  public string foo() "
				+ "  {"
				+ "    return \"test2\";"
				+ "  }"
				+ "}";
		String test = "using NUnit.Framework;"
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
		failed(this.runner.run(Runner.Language.CSHARP, code, test));
	}

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

	@Test
	public void testCSharp_Timeout() {
		String code = "public class Foo {\n"
				+ "  public string foo() \n"
				+ "  {\n"
				+ "    int i = 0;\n"
				+ "    while(i == 0) { i = 0; }\n"
				+ "    return \"test\"\n;"
				+ "  }\n"
				+ "}";
		String test = "using NUnit.Framework;\n"
				+ " [TestFixture]\n"
				+ " public class FooTest\n"
				+ " {\n"
				+ "   [Test]\n"
				+ "   public void testFoo()\n"
				+ "   {\n"
				+ "     Foo foo = new Foo();\n"
				+ "     string result = foo.foo();\n"
				+ "     Assert.That(result, Is.EqualTo(\"test\"));\n"
				+ "   }\n"
				+ " }";
		timeout(this.runner.run(Runner.Language.CSHARP, 60000, code, test));
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
		String code = "public class Foo {\n" +
				"    public String add (long a, long b) {\n" +
				"        return \"test\";\n" +
				"    }\n" +
				"}";
		String test = "import org.junit.Test;\n" +
				"import static org.junit.Assert.assertEquals;\n" +
				"import static org.junit.Assert.assertTrue;\n" +
				"public class FooTest {\n" +
				"@Test public void testFoo() {\n" +
				"Foo foo = new Foo();\n" +
				"String added = foo.add(1L,2L);\n" +
				"assertEquals(\"test\",added);\n" +
				"assertTrue(added instanceof String);\n" +
				"}\n" +
				"@Test public void testFoo2() {\n" +
				"Foo foo = new Foo();\n" +
				"String added = foo.add(1L,2L);\n" +
				"assertEquals(\"test\",added);\n" +
				"assertTrue(added instanceof String);\n" +
				"}\n" +
				"}";

		success(this.runner.run(Runner.Language.JAVA, code, test));
	}

	@Test
	public void testJava_Timeout() {
		String code = 	"public class Foo {" +
				"    public String add (long a, long b) {" +
				"        while(a < b) {" +
				"           b++;        " +
				"    }" +
				"        return \"test\";" +
				"    }"+
				"}";
		String test =    "import org.junit.Test;" +
				"import static org.junit.Assert.assertEquals;" +
				"public class FooTest {" +
				"@Test public void testFoo() {" +
				"Foo foo = new Foo();" +
				"String added = foo.add(1L,2L);" +
				"assertEquals(\"test\", added);" +
				"}" +
				"}";

		timeout(runner.run(Runner.Language.JAVA, 60000, code, test));
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

		timeout(this.runner.run(Runner.Language.JAVASCRIPT, 60000, code, test));
	}
}
