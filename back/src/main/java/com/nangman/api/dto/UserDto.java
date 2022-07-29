package com.nangman.api.dto;

import com.nangman.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 *  InnerStaticClass UserDto 한 번에 관리하는 클래스
 */

@ApiModel("User Model")
public class UserDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Info {

        @ApiModelProperty(name="유저 id(식별자)", example="1")
        private long id;

        @ApiModelProperty(name="유저 이메일", example="nangman@naver.com")
        private String useremail;

        @ApiModelProperty(name="유저 패스워드", example="yourpassword")
        private String password;

        @ApiModelProperty(name="유저 생일", example="1995-11-15")
        private String userBirthday;

        public Info(User user){
            this.id = user.getId();
            this.password = null;
            this.useremail = user.getUseremail();
            this.userBirthday = user.getUserBirthday();
        }
    }

    @Getter
    @Setter
    public static class LoginRequest {
        @ApiModelProperty(name="유저 이메일", example="nangman@naver.com")
        String useremail;

        @ApiModelProperty(name="유저 패스워드", example="yourpassword")
        String password;
    }

    @Getter
    @Setter
    public static class RegisterRequest {
        @ApiModelProperty(name="유저 이메일", example="nangman@naver.com")
        String useremail;

        @ApiModelProperty(name="유저 패스워드", example="yourpassword")
        String password;

        @ApiModelProperty(name="유저 생일", example="1995-11-15")
        String userBirthday;
    }

}
