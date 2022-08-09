package com.nangman.api.service;

import com.nangman.api.dto.ReportDto;
import com.nangman.common.constants.ErrorCode;
import com.nangman.common.exception.CustomException;
import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Report;
import com.nangman.db.entity.UserReport;
import com.nangman.db.repository.ChatInOutRecordRepository;
import com.nangman.db.repository.ReportRepository;
import com.nangman.db.repository.RoomRepository;
import com.nangman.db.repository.UserReportRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService{

    UserReportRepository userReportRepository;
    ReportRepository reportRepository;
    RoomRepository roomRepository;
    ChatInOutRecordRepository chatInOutRecordRepository;
    @Override
    public List<Report> getReportsByUserId(long userId) {
        List<Report> reports = new ArrayList<>();
        List<UserReport> userReports = userReportRepository.findUserReportsByUserId(userId);

        for (int i = 0; i < userReports.size(); i++){
            Report report = userReports.get(i).getReport();
            reports.add(report);
        }
        return reports;
    }

    @Override
    public ReportDto.Info getReportByIds(ReportDto.DetailRequest detailRequest) {
        Report report = reportRepository.findReportById(detailRequest.getReportId());
        int accessTime = 0;
        ChatInOutRecord chatInOutRecord = chatInOutRecordRepository
                .findChatInOutRecordByUserIdAndRoomId(
                detailRequest.getUserId(),
                roomRepository.findRoomByReport(report).getId()
                );

        boolean isInReport = false;

        List<UserReport> checkList = report.getUsers();

        for (UserReport userReport: checkList){
            if (userReport.getUser().getId() == detailRequest.getUserId()){
                isInReport = true;
                break;
            }
        }

        if (!isInReport) throw new CustomException(ErrorCode.REPORT_NOT_FOUND);

        accessTime = getAccessTime(accessTime, chatInOutRecord);

        return new ReportDto.Info(report.getId(), report.getContent(), report.getAverageTime(), report.getTotalChatCount(),
                report.getTotalUserCount(), accessTime);
    }

    private int getAccessTime(int accessTime, ChatInOutRecord chatInOutRecord) {
        accessTime += (chatInOutRecord.getOutTime().getYear() - chatInOutRecord.getInTime().getYear()) * 31536000;
        accessTime += (chatInOutRecord.getOutTime().getDayOfYear() - chatInOutRecord.getInTime().getDayOfYear()) * 86400;
        accessTime += (chatInOutRecord.getOutTime().getHour() - chatInOutRecord.getInTime().getHour()) * 3600;
        accessTime += (chatInOutRecord.getOutTime().getMinute() - chatInOutRecord.getInTime().getMinute()) * 60;
        accessTime += (chatInOutRecord.getOutTime().getSecond() - chatInOutRecord.getInTime().getSecond());
        return accessTime;
    }
}
