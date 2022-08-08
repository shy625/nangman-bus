package com.nangman.api.service;

import com.nangman.api.dto.ChatInOutRecordDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ChatInOutRecordService {
    @Transactional
    ChatInOutRecordDto.Info insertInRecord(ChatInOutRecordDto.ServiceRequest serviceRequest);
    @Transactional
    ChatInOutRecordDto.Info insertOutRecord(ChatInOutRecordDto.ServiceRequest serviceRequest);
}
