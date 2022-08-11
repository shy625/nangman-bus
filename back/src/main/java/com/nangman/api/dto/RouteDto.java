package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
}
