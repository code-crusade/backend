package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
	Optional<Report> findByExercise_IdAndSubmission_Id(Integer exerciseId, Integer submissionId);
}
