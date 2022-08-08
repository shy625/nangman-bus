package com.nangman.api.service;

import com.nangman.api.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    @Override
    @Transactional
    public void InsertChatLogs(ChatDto.ChatLog chatLog) {
        List<ChatDto.MsgLog> list = chatLog.getChatLogs();
        for (ChatDto.MsgLog item : list){

        }
    }
}
