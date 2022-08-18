package com.nangman.api.service;

import com.nangman.api.dto.ReportDto;
import com.nangman.common.constants.ErrorCode;
import com.nangman.common.exception.CustomException;
import com.nangman.common.util.TimeCalculator;
import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Report;
import com.nangman.db.entity.User;
import com.nangman.db.entity.UserReport;
import com.nangman.db.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final UserRepository userRepository;
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
        int chatPerMinute = (int)(report.getTotalChatCount() / (report.getAverageTime() / 60));
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

        //보고서를 작성한 시점에서 유저가 해당 버스를 몇번이나 탔는지
        int personalCount = 0;
        User user = userRepository.findByIdAndIsDeletedFalse(detailRequest.getUserId()).get();
        LocalDateTime reportedTime = report.getCreatedDate();
        List<UserReport> userReportList = user.getUserReports();
        for (UserReport userReport : userReportList){
            if (TimeCalculator.getAccessTime(reportedTime, userReport.getCreatedDate()) >= 0) personalCount++;
        }

        return new ReportDto.Info(report, accessTime, chatPerMinute, personalCount);
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
