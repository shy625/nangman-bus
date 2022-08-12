package com.nangman.api.service;

import com.nangman.api.dto.ChatInOutRecordDto;
import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.repository.ChatInOutRecordRepository;
import com.nangman.db.repository.RoomRepository;
import com.nangman.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        chatInOutRecord.setRoom(roomRepository.findById(serviceRequest.getRoomId()).get());
        chatInOutRecord.setInTime(inTime);
        chatInOutRecordRepository.save(chatInOutRecord);

        ChatInOutRecordDto.Info info = new ChatInOutRecordDto.Info();
        info.setInTime(inTime);
        return info;
    }

    @Override
    @Transactional
    public ChatInOutRecordDto.Info insertOutRecord(ChatInOutRecordDto.ServiceRequest serviceRequest) {
        ChatInOutRecord chatInOutRecord = chatInOutRecordRepository.
                findTop1ChatInOutRecordByUserIdAndRoomIdOrderByInTimeDesc(serviceRequest.getUserId(),
                        serviceRequest.getRoomId()).get();
        LocalDateTime outTime = LocalDateTime.now();
        chatInOutRecord.setOutTime(outTime);
        chatInOutRecordRepository.save(chatInOutRecord);

        ChatInOutRecordDto.Info info = new ChatInOutRecordDto.Info();
        info.setInTime(chatInOutRecord.getInTime());
        info.setOutTime(outTime);
        return info;
    }
}