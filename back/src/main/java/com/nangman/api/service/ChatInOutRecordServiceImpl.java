package com.nangman.api.service;

import com.nangman.api.dto.ChatInOutRecordDto;
import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Room;
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
public class ChatInOutRecordServiceImpl implements ChatInOutRecordService{
    private final ChatInOutRecordRepository chatInOutRecordRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public ChatInOutRecordDto.Info insertInRecord(ChatInOutRecordDto.ServiceRequest serviceRequest) {
        LocalDateTime inTime = LocalDateTime.now();
        ChatInOutRecord chatInOutRecord = new ChatInOutRecord();
        chatInOutRecord.setUser(userRepository.findByIdAndIsDeletedFalse(serviceRequest.getUserId()).get());
        chatInOutRecord.setRoom(roomRepository.findRoomBySessionId(serviceRequest.getSessionId()).get());
        chatInOutRecord.setInTime(inTime);
        chatInOutRecordRepository.save(chatInOutRecord);

        ChatInOutRecordDto.Info info = new ChatInOutRecordDto.Info();
        info.setInTime(inTime);
        return info;
    }

    @Override
    @Transactional
    public ChatInOutRecordDto.Info insertOutRecord(ChatInOutRecordDto.ServiceRequest serviceRequest) {
        Room room = roomRepository.findRoomBySessionId(serviceRequest.getSessionId()).get();
        ChatInOutRecord chatInOutRecord = chatInOutRecordRepository.
                findTop1ChatInOutRecordByUserIdAndRoomIdOrderByInTimeDesc(serviceRequest.getUserId(),
                        room.getId()).get();
        LocalDateTime outTime = LocalDateTime.now();
        chatInOutRecord.setOutTime(outTime);
        chatInOutRecordRepository.save(chatInOutRecord);

        ChatInOutRecordDto.Info info = new ChatInOutRecordDto.Info();
        info.setInTime(chatInOutRecord.getInTime());
        info.setOutTime(outTime);
        return info;
    }
    @Transactional
    @Override
    public void forceOut(String sessionId){
        Room room = roomRepository.findRoomBySessionId(sessionId).get();
        List<ChatInOutRecord> recordList = room.getChatInOutRecords();
        for (ChatInOutRecord item : recordList){
            if (item.getOutTime() == null) item.setOutTime(LocalDateTime.now());
            chatInOutRecordRepository.save(item);
        }
        roomRepository.save(room);
    }
}