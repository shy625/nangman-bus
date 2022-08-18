package com.nangman.api.dto;

import com.nangman.db.entity.Board;
import com.nangman.db.entity.Report;
import com.nangman.db.entity.Room;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpStatus;

import javax.persistence.OneToOne;
import java.time.format.DateTimeFormatter;


@ApiModel("Report model")
public class ReportDto {


    @Getter
    @Setter
    @AllArgsConstructor
    public static class DetailRequest{
        @ApiModelProperty(name="유저 id(식별자)", example="1")
        private long userId;

        @ApiModelProperty(name="보고서 id(식별자)", example="1")
        private long reportId;
    }


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {

        @ApiModelProperty(name="레포트 id(식별자)", example="1")
        private long id;

        @ApiModelProperty(name="보고서 내용", example="조용하게 흘러 갔습니다.")
        private String content;

        @ApiModelProperty(name="평균 이용 시간 (초 단위)", example="3720")
        private int averageTime;

        @ApiModelProperty(name="전체 채팅의 수", example="13200")
        private long totalChatCount;

        @ApiModelProperty(name="이번 보고서에서의 전체 사용자 수", example="30")
        private int totalUserCount;

        @ApiModelProperty(name="보고서 시점으로 이때까지 이 버스를 이용한 사람 수(누적)", example = "3200")
        private long accumulateUserCount;

        @ApiModelProperty(name="보고서 시점으로 내가 이 버스를 이용한 횟수", example = "13")
        private int personalCount;

        @ApiModelProperty(name="보고서 시점으로 내가 이 버스에 적힌 방명록의 갯수", example = "313")
        private int boardCount;

        @ApiModelProperty(name="조회한 유저의 채팅 이용 시간(시)", example="2540")
        private int myAccessHour;

        @ApiModelProperty(name="조회한 유저의 채팅 이용 시간(분))", example="2540")
        private int myAccessMinute;

        @ApiModelProperty(name="채팅방 분당 화력", example="30")
        private int chatPerMinute;

        @ApiModelProperty(name="낭만보고서 작성일", example="2022-08-08(YYYY-MM-DD)")
        private String createDay;

        @ApiModelProperty(name="낭만보고서 작성시간", example="12:34:12(HH:mm:ss)")
        private String createTime;
        public Info(Report report, int accessTime, int chatPerMinute, int personalCount) {
            this.id = report.getId();
            this.content = report.getContent();
            this.totalChatCount = report.getTotalChatCount();
            this.totalUserCount = report.getTotalUserCount();
            this.myAccessHour = accessTime / 3600;
            this.myAccessMinute = (accessTime % 3600) / 60;
            this.chatPerMinute = chatPerMinute;
            this.createDay = report.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.createTime = report.getCreatedDate().format(DateTimeFormatter.ofPattern("HH:mm"));
            this.personalCount = personalCount;
            this.accumulateUserCount = report.getAccumulateUserCount();
            this.boardCount = report.getBoardCount();
        }
    }
}
