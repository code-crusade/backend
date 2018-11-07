package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.CodeValidationResults;
import com.etsmtl.codecrusade.repository.CodeValidationResultsRepository;
import com.etsmtl.codecrusade.service.CodeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeValidationServiceImpl implements CodeValidationService {

	private final CodeValidationResultsRepository codeValidationResultsRepository;

	@Autowired
	public CodeValidationServiceImpl(CodeValidationResultsRepository codeValidationResultsRepository) {
		this.codeValidationResultsRepository = codeValidationResultsRepository;
	}

	@Override
	public Iterable<CodeValidationResults> getAllResultsForExerciseAndSubmission(Integer exerciseId,
			Integer submissionId) {
		return codeValidationResultsRepository.findAllByTest_Exercise_IdAndSubmission_Id(exerciseId, submissionId);
	}
}
