package com.etsmtl.codecrusade.entities.validation;

import com.etsmtl.codecrusade.entities.Submission;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates a non-null Submission instance for language coherency.
 */
public class SubmissionArgumentsValidator implements ConstraintValidator<SubmissionArgumentsValid, Submission> {

    @Override
    public void initialize(SubmissionArgumentsValid contactNumber) {
    }

    @Override
    public boolean isValid(Submission submission, ConstraintValidatorContext cxt) {
        return submission.getExercise() != null &&
               submission.getExercise().getFixtures() != null &&
               submission.getExercise().getFixtures().containsKey(submission.getLanguage());
    }

}