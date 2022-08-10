package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ApiModel("busstop model")
public class BusstopDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{

        @ApiModelProperty(name="노선id", example="GGB219000703")
        private String code;
        @ApiModelProperty(name="도시코드", example="23")
        private int cityCode;
    }
}
