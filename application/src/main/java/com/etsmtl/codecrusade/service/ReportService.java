package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.Report;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Interface for all report-related operations.
 */
public interface ReportService {
    @PreAuthorize("hasRole('ADMIN')")
    @PostFilter("hasPermission(filterObject,'READ')")
    Iterable<Report> findAllReportsForUser(Integer userId);
}
