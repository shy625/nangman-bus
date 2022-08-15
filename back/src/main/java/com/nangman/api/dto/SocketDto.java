package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("Socket model")
public class SocketDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PubUserInOut {
        private Long userId;
        private String message;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubUserInOut {
        private Long userId;
        private Integer inOut;  // 1 : in, 2 : out
        private String message;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PubChat {
        private Long userId;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubChat {
        private Long chatId;
        private Long userId;
        private String message;
        private String createdTime;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatLike {
        private Long chatId;
        private Integer count;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatBusStop {
        private Long busStopId;
        private String busStopName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatUserEmotion {
        private Long userId;
        private Integer emotion;    // 0 : 무표정, 1 : 화나요, 2 : 기분좋아요, 3 : 우울해요
    }

}
