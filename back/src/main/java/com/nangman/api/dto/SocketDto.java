package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("Socket model")
public class SocketDto {

    @Getter
    public static class ChatPub {
        private String userId;
        private String message;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatSub {
        private String chatId;
        private String userId;
        private String message;
        private String createdTime;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatLike {
        private String chatId;
        private Integer count;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatUser {
        private String userId;
        private Integer inOut;  // in : 1, out : 2
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatBusStop {
        private Long busStopId;
        private String busStopName;
    }

}
