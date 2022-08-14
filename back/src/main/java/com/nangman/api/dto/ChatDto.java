
package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@ApiModel("Chat model")
public class ChatDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MsgLog{

        @ApiModelProperty(name="채팅 id", example="1")
        private String chatId;

        @ApiModelProperty(name="유저 id", example="1")
        private String userId;

        @ApiModelProperty(name="채팅 생성 시간", example="2021-08-01T16:26:39.098")
        private String createdTime;

        @ApiModelProperty(name="채팅 내용", example="안녕하살법")
        private String content;

        @ApiModelProperty(name="채팅에 대한 좋아요 수", example="1")
        private String like;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatLog{
        @ApiModelProperty(name="채팅방 세션 id", example="session_123123124325")
        private String sessionId;

        @ApiModelProperty(name="버스 번호판", example="77바8236")
        private String licenseNo;

        @ApiModelProperty(name="버스 노선 번호", example="GGB234000878")
        private String routeId;

        @ApiModelProperty(name="채팅방 생성 시간", example="2021-08-01T16:26:39.098")
        private String createdDate;

        @ApiModelProperty(name="채팅 로그들", example="MsgLog 참고, Msglog의 리스트형태")
        private List<MsgLog> chatLogs;
    }
}
