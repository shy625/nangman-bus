package com.nangman.api.service;

import com.nangman.api.dto.ReportDto;
import com.nangman.common.constants.ErrorCode;
import com.nangman.common.exception.CustomException;
import com.nangman.common.util.TimeCalculator;
import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Report;
import com.nangman.db.entity.UserReport;
import com.nangman.db.repository.ChatInOutRecordRepository;
import com.nangman.db.repository.ReportRepository;
import com.nangman.db.repository.RoomRepository;
import com.nangman.db.repository.UserReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final UserReportRepository userReportRepository;
    private final ReportRepository reportRepository;
    private final RoomRepository roomRepository;
    private final ChatInOutRecordRepository chatInOutRecordRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Report> getReportsByUserId(long userId) {
        List<Report> reports = new ArrayList<>();
        List<UserReport> userReports = userReportRepository.findUserReportsByUserIdOrderByCreatedDateDesc(userId);

        for (int i = 0; i < userReports.size(); i++){
            Report report = userReports.get(i).getReport();
            reports.add(report);
        }

        return reports;
    }

    @Override
    @Transactional(readOnly = true)
    public ReportDto.Info getReportByIds(ReportDto.DetailRequest detailRequest) {
        Report report = reportRepository.findReportById(detailRequest.getReportId()).get();
        int accessTime = 0;
        List<ChatInOutRecord> chatInOutRecordList = chatInOutRecordRepository
                .findChatInOutRecordByUserIdAndRoomId(
                detailRequest.getUserId(),
                roomRepository.findRoomByReport(report).get().getId()
                );

        boolean isInReport = false;

        List<UserReport> checkList = report.getUserReports();

        for (UserReport userReport: checkList){
            if (userReport.getUser().getId() == detailRequest.getUserId()){
                isInReport = true;
                break;
            }
        }

        if (!isInReport) throw new CustomException(ErrorCode.REPORT_NOT_FOUND);

        for (ChatInOutRecord item : chatInOutRecordList)
            accessTime += TimeCalculator.getAccessTime(item.getOutTime(), item.getInTime());

        return new ReportDto.Info(report.getId(), report.getContent(), report.getAverageTime(), report.getTotalChatCount(),
                report.getTotalUserCount(), accessTime);
    }

    @Override
    @Transactional
    public Report creatReport() {
        Report report = new Report();
        reportRepository.save(report);
        report = reportRepository.findReportById(report.getId()).get();
        return report;
    }

    @Override
    @Transactional
    public Report updateReport(Report report) {
        reportRepository.save(report);
        report = reportRepository.findReportById(report.getId()).get();
        return report;
    }

}
