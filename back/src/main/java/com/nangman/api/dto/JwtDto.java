package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * JWT DTO 정의
 */

// session에 필요한 객체는 Serializable implements하기!(사사갤 참조)
@Getter
@Setter
@RequiredArgsConstructor
@ApiModel("Jwt Model")
public class JwtDto implements Serializable {

    @ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String accessToken;

}
