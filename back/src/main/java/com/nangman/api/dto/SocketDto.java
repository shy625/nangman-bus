package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel("Socket model")
public class SocketDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PubUserInOut {
        private Long userId;
        private String message;
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubUserInOut {
        private Long userId;
        private Integer inOut;  // 1 : in, 2 : out
        private String message;
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PubChat {
        private Long userId;
        private String message;
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubChat {
        private Long chatId;
        private Long userId;
        private String message;
        private Integer emotion;
        private String createdTime;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatLike {
        private Long chatId;
        private Integer count;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubBusStop {
        private Long prevId;
        private String prevName;
        private Long curId;
        private String curName;
        private Long nextId;
        private String nextName;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserOutBusStop {
        private Long userId;
        private Long busStopId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserEmotion {
        private Long userId;
        private Integer emotion;    // 0 : 무표정, 1 : 화나요, 2 : 기분좋아요, 3 : 우울해요
    }

}
