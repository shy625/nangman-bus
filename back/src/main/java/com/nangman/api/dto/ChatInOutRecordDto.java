package com.nangman.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class ChatInOutRecordDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{
        @ApiModelProperty(name="채팅방 id", example="1")
        private Long roomId;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServiceRequest {

        @ApiModelProperty(name="채팅방 id", example="1")
        private Long roomId;

        @ApiModelProperty(name="유저 id", example="1")
        private Long userId;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {

        @ApiModelProperty(name="입장시간", example="2021-08-01T16:26:39.098")
        private LocalDateTime inTime;

        @ApiModelProperty(name="퇴장시간", example="2021-08-01T16:26:39.098")
        private LocalDateTime outTime;
    }
}
