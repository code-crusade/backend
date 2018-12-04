package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.repository.ReportRepository;
import com.etsmtl.codecrusade.service.CodeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CodeValidationServiceImpl implements CodeValidationService {

	private final ReportRepository reportRepository;

	@Autowired
	public CodeValidationServiceImpl(ReportRepository codeValidationResultsRepository) {
		this.reportRepository = codeValidationResultsRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Report> getReportForExerciseAndSubmission(Integer exerciseId,
																  Integer submissionId) {
		return reportRepository.findByExercise_IdAndSubmission_Id(exerciseId, submissionId);
	}
}
