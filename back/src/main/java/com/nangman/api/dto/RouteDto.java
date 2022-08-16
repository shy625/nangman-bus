package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@ApiModel("route model")
public class RouteDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{

        @ApiModelProperty(name="버스번호(문자열)", example="8100")
        private String no;
        @ApiModelProperty(name="도시이름 한글로", example="시흥")
        private String cityName;
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info{

        @ApiModelProperty(name="노선id", example="8100")
        private String code;
        @ApiModelProperty(name="노선번호", example="ICB165000160")
        private String routeNo;
        @ApiModelProperty(name="시작시간", example="0500")
        private String startTime;
        @ApiModelProperty(name="종료시간", example="2300")
        private String endTime;
        @ApiModelProperty(name="기점", example="숭의역(1번출구)")
        private String startBusStop;
        @ApiModelProperty(name="종점", example="강남서초현대타워앞")
        private String endBusStop;
        @ApiModelProperty(name="버스종류", example="광역버스")
        private String routeType;
        @ApiModelProperty(name="도시코드", example="23")
        private int cityCode;
        @ApiModelProperty(name="정류소 정보 리스트", example="BusstopInfo참조")
        private List<BusstopInfo> busstopInfoList;
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BusstopInfo{
        @ApiModelProperty(name="정류장이름", example="숭의역")
        private String nodeName;
        @ApiModelProperty(name="정류장순서", example="23")
        private int nodeOrd;
    }
}
