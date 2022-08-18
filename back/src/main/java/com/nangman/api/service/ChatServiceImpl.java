package com.nangman.api.service;

import com.nangman.api.dto.ChatDto;
import com.nangman.common.util.TimeCalculator;
import com.nangman.db.entity.*;
import com.nangman.db.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final RoomRepository roomRepository;
    private final ReportService reportService;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatInOutRecordRepository chatInOutRecordRepository;
    private final UserReportRepository userReportRepository;
    private final RoomService roomService;
    private final BoardRepository boardRepository;
    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public void InsertChatLogs(ChatDto.ChatLog chatLog) {
        log.info(chatLog.toString());
        //report init
        Report report = reportService.creatReport();
        Room room = roomRepository.findRoomBySessionId(chatLog.getSessionId()).get();
        report.setRoom(room);
        int checkBigLike = 0;
        int accessTime = 0;
        String reportMsg = "";
        List<ChatDto.MsgLog> msgList = chatLog.getChatLogs();
        Set<User> userList = new HashSet<>();
        List<ChatInOutRecord> roomUserLog = chatInOutRecordRepository.findChatInOutRecordByRoomId(room.getId());
        for (ChatDto.MsgLog item : msgList){
            Chat chat = new Chat();
            User user = userRepository.findByIdAndIsDeletedFalse(Integer.parseInt((item.getUserId()))).get();
            int tempLike = Integer.parseInt(item.getLike());
            if (checkBigLike <= tempLike){
                checkBigLike = tempLike;
                reportMsg = item.getContent();
            }
            chat.setContent(item.getContent());
            chat.setLikes(tempLike);
            chat = createChat(chat);
            user.addChat(chat);
            room.addChat(chat);
        }
        for (ChatInOutRecord item : roomUserLog) userList.add(item.getUser());

        //report update할 내용 세팅
        report.setContent(reportMsg);
        report.setTotalChatCount(chatLog.getChatLogs().size());
        report.setTotalUserCount(userList.size());
        for (User item : userList){
            UserReport userReport = new UserReport();
            userReportRepository.save(userReport);
            List<ChatInOutRecord> chatInOutRecordList = chatInOutRecordRepository.
                    findChatInOutRecordByUserIdAndRoomId(item.getId(), room.getId());
            for (ChatInOutRecord inOutRecord : chatInOutRecordList){
                room.addChatInOutRecord(inOutRecord);
                accessTime += TimeCalculator.getAccessTime(inOutRecord.getOutTime(), inOutRecord.getInTime());
            }
            item.addUserReport(userReport);
            report.addUserReport(userReport);
        }
        int accumulateUserCount = 0;
        List<Report> reportList = reportRepository.findAll();
        for (Report item : reportList)
            if (item.getRoom().getBus().getLicenseNo().equals(room.getBus().getLicenseNo())) accumulateUserCount += item.getTotalUserCount();
        report.setAccumulateUserCount(accumulateUserCount);
        report.setBoardCount(boardRepository.findBoardByBusId(room.getBus().getId()).size());
        if (userList.size() == 0) report.setAverageTime(0);
        else report.setAverageTime(accessTime / userList.size());
        reportService.updateReport(report);

        //room update할 내용 세팅
        room.setTerminatedDate(LocalDateTime.now());
        room.setReport(report);
        roomService.updateRoom(room);
    }

    @Transactional
    public Chat createChat(Chat chat){
        chatRepository.save(chat);
        chat = chatRepository.findById(chat.getId()).get();
        return chat;
    }
}
