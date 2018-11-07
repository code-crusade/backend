package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Exercise;
import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.entities.embeddable.SubmissionArgument;
import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.repository.SubmissionRepository;
import com.etsmtl.codecrusade.repository.UserRepository;
import com.etsmtl.codecrusade.service.ApplicationUserDetailsService;
import com.etsmtl.codecrusade.service.ExerciseService.ExerciseNotFoundException;
import com.etsmtl.codecrusade.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Service
@Validated
public class SubmissionServiceImpl implements SubmissionService {

	private final ExerciseRepository            exerciseRepository;
	private final ApplicationUserDetailsService userDetailsService;
	private final UserRepository                userRepository;
	private       SubmissionRepository          submissionRepository;

	@Autowired
	public SubmissionServiceImpl(SubmissionRepository submissionRepository, ExerciseRepository exerciseRepository,
			UserRepository userRepository, ApplicationUserDetailsService applicationUserDetailsService) {
		this.submissionRepository = submissionRepository;
		this.exerciseRepository = exerciseRepository;
		this.userDetailsService = applicationUserDetailsService;
		this.userRepository = userRepository;
	}

	@Override
	public Optional<Submission> getSubmissionByExercise(Integer exerciseId, Integer submissionId) {
		return submissionRepository.findByExercise_IdAndId(exerciseId, submissionId);
	}

	@Override
	public Iterable<Submission> getAllSubmissionsByExercise(Integer exerciseId) {
		return submissionRepository.findAllByExercise_Id(exerciseId);
	}

	@Override
	public Optional<Submission> createSubmissionForExercise(Integer exerciseId, SubmissionArgument submissionArgument)
			throws UserNotAllowedException, ExerciseNotFoundException {
		Objects.requireNonNull(exerciseId);
		Objects.requireNonNull(submissionArgument);
		// find exercise or throw
		Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotFoundException::new);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepository.findByUsername(currentPrincipalName);
		// ensure no submission exists
		if (submissionRepository.countByExercise_IdAndUser_Id(exerciseId, user.getId()) == 0) {
			Submission submission = Submission.builder()
											  .exercise(exercise)
											  .program(submissionArgument)
											  .user(user)
											  .build();

			submission = saveSubmission(submission);

			if (submission.getId() != null) {
				return Optional.of(submission);
			}
		}
		return Optional.empty();
	}

	/**
	 * Saves submission, with validation guard.
	 *
	 * @param submission the submission to save
	 * @return the saved submission, with a generated id if successful
	 */
	private Submission saveSubmission(@Valid Submission submission) {
		return submissionRepository.save(submission);
	}
}
