package com.nangman.api.service;

import com.nangman.api.dto.ChatDto;
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

    RoomRepository roomRepository;
    ReportService reportService;
    ChatRepository chatRepository;
    UserRepository userRepository;
    ChatInOutRecordRepository chatInOutRecordRepository;
    UserReportRepository userReportRepository;

    RoomService roomService;

    @Override
    @Transactional
    public void InsertChatLogs(ChatDto.ChatLog chatLog) {
        //report init
        Report report = reportService.creatReport();
        Room room = roomRepository.findRoomBySessionId(chatLog.getSessionId()).get();
        report.setRoom(room);
        int checkBigLike = 0;
        int accessTime = 0;
        String reportMsg = "";
        List<ChatDto.MsgLog> msgList = chatLog.getChatLogs();
        Set<User> userList = new HashSet<>();

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
            user.addChatting(chat);
            room.addChat(chat);
        }

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
                accessTime += reportService.getAccessTime(inOutRecord);
            }
            item.addUserReport(userReport);
            report.addUserReport(userReport);
        }
        report.setAverageTime(accessTime / userList.size());
        reportService.updateReport(report);

        //room update할 내용 세팅
        room.setTerminatedDate(LocalDateTime.now());
        room.setReport(report);
        roomService.updateRoom(room);
    }

    public Chat createChat(Chat chat){
        chatRepository.save(chat);
        chat = chatRepository.findById(chat.getId()).get();
        return chat;
    }
}
