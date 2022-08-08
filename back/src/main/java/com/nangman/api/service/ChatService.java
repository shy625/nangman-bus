package com.nangman.api.service;

import com.nangman.api.dto.ChatDto;
import org.springframework.stereotype.Service;

@Service
public interface ChatService {
    void InsertChatLogs(ChatDto.ChatLog chatLog);
}
