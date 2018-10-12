package com.etsmtl.codecrusade.runner;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import javax.validation.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import static com.etsmtl.codecrusade.runner.ExecutionResult.ExecutionStatus.FAILURE;
import static com.etsmtl.codecrusade.runner.ExecutionResult.ExecutionStatus.SUCCESS;

/**
 * Runs java sources with provided arguments.
 */
public class JavaRunner implements Runner {
	@Override
	public ExecutionResult evaluate(String rawCode, RunnerArguments args) {
		try {
			boolean success = evaluateJava(rawCode, args);
			return ExecutionResult.builder()
								  .message(success ? "Success!" : "Failure !")
								  .status(success ? SUCCESS : FAILURE)
								  .build();
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
		return ExecutionResult.builder().message("Failed !").status(FAILURE).build();
	}

	private boolean evaluateJava(String rawCode, RunnerArguments runnerArgs) {
		String code = Objects.requireNonNull(rawCode);
		RunnerArguments args = Objects.requireNonNull(runnerArgs);
		// Validation
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<RunnerArguments>> violations = validator.validate(args);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException("Invalid runner arguments", violations);
		}

		// TODO : use buffers
		// compilation
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);

		out.print(code);
		out.close();

		JavaFileObject file = new JavaSourceFromString(args.getClassName(), writer.toString());

		Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
		CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

		boolean success = task.call();
		if (success) {
			try {

				URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("").toURI().toURL() });
				Class newClass = Class.forName(args.getClassName(), true, classLoader);
				Method declaredMethod = newClass.getDeclaredMethod(args.getMethodName(), args.getParameterTypes());
				Object result = declaredMethod.invoke(newClass.getDeclaredConstructor().newInstance(),
													  args.getMethodArguments());
				return args.getExpectedResult().equals(result);

			} catch (ClassNotFoundException e) {
				System.err.println("Class not found: " + e);
			} catch (NoSuchMethodException e) {
				System.err.println("No such method: " + e);
			} catch (MalformedURLException e) {
				System.err.println("Malformed url: " + e);
			} catch (IllegalAccessException e) {
				System.err.println("Illegal access : " + e);
			} catch (InstantiationException e) {
				System.err.println("Instantiation error : " + e);
			} catch (InvocationTargetException e) {
				System.err.println("Invocation error :" + e);
			}
			/*new ByteBuddy().subclass(Runner.class)
						   .method(named("evaluate").and(
								   isDeclaredBy(Runner.class).and(returns(ExecutionResult.class))));*/
		}
		else {
			// gather diagnostics and throw
			throw new IllegalArgumentException("Could not compile raw code to usable java source.");
		}
		return false;
	}

	public static void main(String... args) {
		// TODO : add strict security manager policies
	}

	/**
	 * A java source file from a String. From https://stackoverflow.com/questions/12173294/compile-code-fully-in-memory-with-javax-tools-javacompiler
	 */
	class JavaSourceFromString extends SimpleJavaFileObject {
		final String code;

		JavaSourceFromString(String name, String code) {
			super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.code = code;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return code;
		}
	}
}
