package com.nangman.api.dto;

import com.nangman.db.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;


/**
 *  InnerStaticClass BoardDto 한 번에 관리하는 클래스
 */

// session에 필요한 객체는 Serializable implements하기!(사사갤 참조)
@ApiModel("Board Model")
public class BoardDto implements Serializable {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Info {

        @ApiModelProperty(name="방명록 id(식별자)", example="1")
        private long boardId;

        @ApiModelProperty(name="유저 id(식별자)", example = "1")
        private long userId;

        @ApiModelProperty(name="버스 id(식별자)", example="1")
        private long busId;

        @ApiModelProperty(name="유저 이메일(식별자)", example = "nangman@naver.com")
        private String useremail;

        @ApiModelProperty(name="방명록 내용", example="오늘 하루도 화이팅~")
        private String content;

        @ApiModelProperty(name="방명록 작성일", example="2022-08-08(YYYY-MM-DD)")
        private String createDay;

        @ApiModelProperty(name="방명록 작성시간", example="12:34:12(HH:mm:ss)")
        private String createTime;

        @ApiModelProperty(name = "방명록 색상", example = "'#FFD96A', '#FF9090', '#FFB6B9'")
        private String color;

        public Info(Board board, String color) {
            this.boardId = board.getId();
            this.userId = board.getUser().getId();
            this.busId = board.getBus().getId();
            this.useremail = board.getUser().getUseremail();
            this.content = board.getContent();
            this.createDay = board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.createTime = board.getCreatedDate().format(DateTimeFormatter.ofPattern("HH:mm"));
            this.color = color;
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class createBoardRequest {

        @NotNull
        @ApiModelProperty(name="유저 id(식별자)", example = "1")
        private long userId;

        @NotNull
        @ApiModelProperty(name="차량 id(식별자)", example = "1")
        private long busId;

        @NotEmpty
        @ApiModelProperty(name="방명록 내용", example="오늘 하루도 화이팅~")
        private String content;

    }
}
