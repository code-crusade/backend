package com.etsmtl.codecrusade.entities.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Annotation marking a submission for validation between its exercise and arguments.
 */
@Documented
@Constraint(validatedBy = SubmissionArgumentsValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SubmissionArgumentsValid {
	String message() default "Submission arguments must match exercise.";
}
