package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@ApiModel(value = "ErrorDto (에러 관련 정보)", description = "Error code 정보를 전달해 준다.")
public class ErrorDto {

    @ApiModelProperty(value = "Error Http Status code")
    private int status;
    @ApiModelProperty(value = "Error message")
    private String message;

    public ErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

}