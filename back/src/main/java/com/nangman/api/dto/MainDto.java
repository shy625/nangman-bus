package com.nangman.api.dto;


import com.nangman.db.entity.Bus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("MainPage Model")
public class MainDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info{
        @ApiModelProperty(name="가장 많이 탄 버스 TOP3", example="Bus Entity 참고")
        List<BusDto.Info> top3;
        @ApiModelProperty(name="가장 많이 탄 버스 TOP3의 탑승횟수", example="1,2,3")
        List<Integer> top3Count;
        @ApiModelProperty(name="가장 최근에 이용한 버스 정보", example="MainDto.RecentBus 참고")
        RecentBus recentBus;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RecentBus{
        @ApiModelProperty(name="가장 최근에 이용한 버스", example="Bus Entity 참고")
        BusDto.Info bus;
        @ApiModelProperty(name="하차 이후 쌓인 방명록 갯수", example="1")
        int countBoard;
        @ApiModelProperty(name="버스 이용 시간", example="2021-08-01T16:26:39.098")
        LocalDateTime takenTime;
    }

}
