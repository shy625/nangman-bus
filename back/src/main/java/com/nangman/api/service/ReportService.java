package com.nangman.api.service;

import com.nangman.db.entity.Report;
import com.nangman.db.entity.User;

import java.util.List;

public interface ReportService {
    List<Report> getReportsByUserId(Long userId);
}
