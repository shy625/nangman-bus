package com.nangman.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ChatDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MsgLog{
        String chatId;
        String userId;
        String createdTime;
        String content;
        String like;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatLog{
        String roomId;
        String licenseNo;
        String routeId;
        String createdDate;
        List<MsgLog> chatLogs;
    }
}
