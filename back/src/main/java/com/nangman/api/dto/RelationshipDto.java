package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@ApiModel("RelationShip model")
public class RelationshipDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        @ApiModelProperty(name="현재 이용중인 채팅룸의 세션id", example = "session_123123124325")
        public String sessionId;
        @ApiModelProperty(name="상대방의 userId", example = "1")
        public long targetId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info{
        @ApiModelProperty(name="상대방과 같은 시간대의 버스를 탄 횟수", example = "1")
        public int countNumTogether;
        @ApiModelProperty(name="한달동안 해당 버스노선(licenseNo아님)을 이용한 횟수", example = "1")
        public int countMonthlyUsed;
        @ApiModelProperty(name="상대방이 현재버스를 얼마나 탔는지(시)", example = "1")
        public int inHour;
        @ApiModelProperty(name="상대방이 현재버스를 얼마나 탔는지(분)", example = "30")
        public int inMinute;

        public Info(int countNumTogether, int countMonthlyUsed, int inTime) {
            this.countNumTogether = countNumTogether;
            this.countMonthlyUsed = countMonthlyUsed;
            this.inHour = inTime / 3600;
            this.inMinute = (inTime % 3600) / 60;
        }
    }
}
