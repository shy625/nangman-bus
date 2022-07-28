package com.nangman.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


/**
 *  유저 request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
@Getter
@Setter
@RequiredArgsConstructor
@ApiModel("User model")
public class UserDto {

    @ApiModelProperty(name="유저 이메일", example="nangman@naver.com")
    String useremail;

    @ApiModelProperty(name="유저 패스워드", example="yourpassword")
    String password;

    @ApiModelProperty(name="유저 생일", example="1995-11-15")
    String birthday;

    public UserDto(String useremail, String password, String birthday) {
        this.useremail = useremail;
        this.password = password;
        this.birthday = birthday;
    }

    public UserDto(String useremail, String birthday) {
        this.useremail = useremail;
        this.birthday = birthday;
    }


}
