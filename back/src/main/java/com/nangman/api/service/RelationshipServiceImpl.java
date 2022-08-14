package com.nangman.api.service;

import com.nangman.api.dto.RelationshipDto;
import com.nangman.common.util.TimeCalculator;
import com.nangman.db.entity.*;
import com.nangman.db.repository.ChatInOutRecordRepository;
import com.nangman.db.repository.RoomRepository;
import com.nangman.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RelationshipServiceImpl implements RelationshipService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ChatInOutRecordRepository chatInOutRecordRepository;

   @Override
   @Transactional(readOnly = true)
    public RelationshipDto.Info createRelationShip(long srcUserId, long targetUserId, String sessionId){
       User target = userRepository.findByIdAndIsDeletedFalse(targetUserId).get();
       User src = userRepository.findByIdAndIsDeletedFalse(srcUserId).get();
       List<UserReport> targetReportList = target.getUserReports();

       //get CountNumTogeter
       int countNum = 0;
       for (UserReport userReport : targetReportList){
           Report report = userReport.getReport();
           List<UserReport> targetUserList = report.getUserReports();
           for (UserReport item : targetUserList){
               if(item.getUser().getId() == srcUserId) {
                   countNum++;
                   break;
               }
           }
       }
       //get CountMonthlyUsed
       int countMonthly = 0;
       Room room = roomRepository.findRoomBySessionId(sessionId).get();
       String routeNo = room.getBus().getRouteNo();
       for (UserReport userReport : targetReportList){
           Report report = userReport.getReport();
           LocalDateTime reportTime = report.getCreatedDate();
           LocalDateTime nowTime = LocalDateTime.now();
           String targetRouteNo = report.getRoom().getBus().getRouteNo();
           if (routeNo.equals(targetRouteNo) &&
                   TimeCalculator.getAccessTime(nowTime, reportTime) < 60 * 60 * 24 * 28)
               countMonthly++;
       }
       //get inTime
       int inTime = 0;
       List<ChatInOutRecord> chatInOutRecordList =
               chatInOutRecordRepository.findChatInOutRecordByUserIdAndRoomId(targetUserId, room.getId());
       for (ChatInOutRecord record : chatInOutRecordList){
           int tempTime = 0;
           if (record.getOutTime() == null) tempTime = TimeCalculator.getAccessTime(LocalDateTime.now(), record.getInTime());
           else tempTime = TimeCalculator.getAccessTime(record.getOutTime(), record.getInTime());
           inTime += tempTime;
       }
       return new RelationshipDto.Info(countNum, countMonthly, inTime);
    }
}
