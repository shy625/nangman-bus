package com.nangman.api.dto;

import com.nangman.db.entity.Room;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

import javax.persistence.OneToOne;


@ApiModel("Report model")
public class ReportDto {
    @Getter
    @AllArgsConstructor
    public static class Info {
        private String content;
        private int averageTime;
        private Long totalChatCount;
        private int totalUserCount;
        private int accessTime;
    }

    @Getter
    @Setter
    public static class Request {

    }
}
