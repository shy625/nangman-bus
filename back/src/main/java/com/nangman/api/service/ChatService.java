package com.nangman.api.service;

import com.nangman.api.dto.ChatDto;
import com.nangman.db.entity.Chat;
import org.springframework.stereotype.Service;

@Service
public interface ChatService {
    void InsertChatLogs(ChatDto.ChatLog chatLog);
    Chat createChat(Chat chat);
}
