package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.CodeValidationResults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValidationResultsRepository extends CrudRepository<CodeValidationResults, Integer> {
	Iterable<CodeValidationResults> findAllByTest_Exercise_IdAndSubmission_Id(Integer exerciseId, Integer submissionId);
}
