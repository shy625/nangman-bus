package com.nangman.api.dto;

import com.nangman.db.entity.Room;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpStatus;

import javax.persistence.OneToOne;



@ApiModel("Report model")
public class ReportDto {


    @Getter
    @Setter
    public static class DetailRequest{
        @ApiModelProperty(name="유저 id(식별자)", example="1")
        private Long userId;

        @ApiModelProperty(name="보고서 id(식별자)", example="1")
        private Long reportId;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {

        @ApiModelProperty(name="유저 id(식별자)", example="1")
        private Long id;

        @ApiModelProperty(name="보고서 내용", example="조용하게 흘러 갔습니다.")
        private String content;

        @ApiModelProperty(name="평균 이용 시간 (초 단위)", example="3720")
        private int averageTime;

        @ApiModelProperty(name="전체 채팅의 수", example="13200")
        private Long totalChatCount;

        @ApiModelProperty(name="전체 사용자 수", example="30")
        private int totalUserCount;

        @ApiModelProperty(name="조회한 유저의 채팅 이용 시간(초 단위)", example="2540")
        private int accessTime;
    }
}
