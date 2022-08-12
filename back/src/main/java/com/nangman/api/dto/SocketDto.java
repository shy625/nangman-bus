package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("Socket model")
public class SocketDto {

    @Getter
    public static class ChatPub {
        private String writer;
        private String message;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatSub {
        private String chatId;
        private String writer;
        private String message;
        private String createdTime;
    }

}
