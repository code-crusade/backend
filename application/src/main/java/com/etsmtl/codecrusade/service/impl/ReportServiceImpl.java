package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.repository.ReportRepository;
import com.etsmtl.codecrusade.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Report> findAllReportsForUser(Integer userId) {
        return reportRepository.findBySubmission_User_Id(userId);
    }
}
