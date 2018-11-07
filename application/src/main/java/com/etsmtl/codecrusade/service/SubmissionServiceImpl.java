package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService {

	private SubmissionRepository submissionRepository;

	@Autowired
	public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
		this.submissionRepository = submissionRepository;
	}

	@Override
	public Optional<Submission> getSubmissionByExercise(Integer exerciseId, Integer submissionId) {
		return submissionRepository.findByExercise_IdAndId(exerciseId, submissionId);
	}
}
