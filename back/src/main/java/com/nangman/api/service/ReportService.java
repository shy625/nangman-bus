package com.nangman.api.service;

import com.nangman.api.dto.ReportDto;
import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ReportService {
    List<ReportDto.Simple> getReportsByUserId(long userId);
    ReportDto.Info getReportByIds(ReportDto.DetailRequest detailRequest);

    @Transactional
    Report creatReport();

    @Transactional
    Report updateReport(Report report);

}
