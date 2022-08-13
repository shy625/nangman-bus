package com.nangman.api.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ApiModel("Room model")
public class RoomDto {
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRequest{

        @ApiModelProperty(name="채팅방 세션 id", example="session_123123124325")
        private String sessionId;
        @ApiModelProperty(name="버스 번호판", example="77바8236")
        private String licenseNo;
    }
}