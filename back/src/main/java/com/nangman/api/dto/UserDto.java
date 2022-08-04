package com.nangman.api.dto;

import com.nangman.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 *  InnerStaticClass UserDto 한 번에 관리하는 클래스
 */

// session에 필요한 객체는 Serializable implements하기!(사사갤 참조)
@ApiModel("User Model")
public class UserDto implements Serializable {


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Info {

        @ApiModelProperty(name="유저 id(식별자)", example="1")
        private long id;

        @Email
        @ApiModelProperty(name="유저 이메일", example="nangman@naver.com")
        private String useremail;

        //'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용
        //(특수문자는 정의된 특수문자만 사용 가능)
//        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")
        @Size(min = 8, max = 16)
        @ApiModelProperty(name="유저 패스워드", example="yourpassword")
        private String password;


        @Pattern(regexp = "^((19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])|)$")
        @ApiModelProperty(name="유저 생일", example="1995-11-15")
        private String userBirthday;

        private String is_rouletted;

//        private String nickname;

        private String whiperMode;


        public Info(User user){
            this.id = user.getId();
            this.password = null;
            this.useremail = user.getUseremail();
            this.userBirthday = user.getUserBirthday();
            this.is_rouletted = user.isRouletted() ? "Y" : "N";
//            this.nickname = user.getNickname().getNickname();
            this.whiperMode = user.getSetting().isWhisperMode() ? "Y" : "N";
        }
    }

    @Getter
    @Setter
    public static class LoginRequest {
        @Email
        @ApiModelProperty(name="유저 이메일", example="nangman@naver.com")
        private String useremail;

        //'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용
        //(특수문자는 정의된 특수문자만 사용 가능)
        @Size(min = 8, max = 16)
        @ApiModelProperty(name="유저 패스워드", example="yourpassword")
        private String password;
    }

    @Getter
    @Setter
    public static class RegisterRequest {
        @Email
        @ApiModelProperty(name="유저 이메일", example="nangman@naver.com")
        private String useremail;

        //'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용
        //(특수문자는 정의된 특수문자만 사용 가능)
//        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")
        @Size(min = 8, max = 16)
        @ApiModelProperty(name="유저 패스워드", example="yourpassword")
        private String password;

        @Pattern(regexp = "^((19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])|)$")
        @ApiModelProperty(name="유저 생일", example="1995-11-15")
        private String userBirthday;
    }

}
