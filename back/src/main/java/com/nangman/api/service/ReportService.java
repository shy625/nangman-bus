package com.nangman.api.service;

import com.nangman.api.dto.ReportDto;
import com.nangman.db.entity.Report;

import java.util.List;

public interface ReportService {
    List<Report> getReportsByUserId(long userId);
    ReportDto.Info getReportByIds(ReportDto.DetailRequest detailRequest);
}
