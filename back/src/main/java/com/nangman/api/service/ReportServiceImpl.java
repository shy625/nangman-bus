package com.nangman.api.service;

import com.nangman.api.dto.ReportDto;
import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Report;
import com.nangman.db.entity.UserReport;
import com.nangman.db.repository.ChatInOutRecordRepository;
import com.nangman.db.repository.ReportRepository;
import com.nangman.db.repository.RoomRepository;
import com.nangman.db.repository.UserReportRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("reportService")
@Slf4j
public class ReportServiceImpl implements ReportService{

    UserReportRepository userReportRepository;
    ReportRepository reportRepository;
    RoomRepository roomRepository;
    ChatInOutRecordRepository chatInOutRecordRepository;
    @Override
    public List<Report> getReportsByUserId(long userId) {
        List<Report> reports = new LinkedList<>();
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
        ChatInOutRecord chatInOutRecord = chatInOutRecordRepository.findChatInOutRecordByUserIdAndRoomId(
                detailRequest.getUserId(),
                roomRepository.findRoomByReport(report).getId());
        accessTime += (chatInOutRecord.getInTime().getYear() - chatInOutRecord.getOutTime().getYear()) * 31536000;
        accessTime += (chatInOutRecord.getInTime().getDayOfYear() - chatInOutRecord.getOutTime().getDayOfYear()) * 86400;
        accessTime += (chatInOutRecord.getInTime().getHour() - chatInOutRecord.getOutTime().getHour()) * 3600;
        accessTime += (chatInOutRecord.getInTime().getMinute() - chatInOutRecord.getOutTime().getMinute()) * 60;
        accessTime += (chatInOutRecord.getInTime().getSecond() - chatInOutRecord.getOutTime().getSecond());
        return new ReportDto.Info(report.getId(), report.getContent(), report.getAverageTime(), report.getTotalChatCount(),
                report.getTotalUserCount(), accessTime);
    }
}
