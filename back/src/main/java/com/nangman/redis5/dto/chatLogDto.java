package com.nangman.redis5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class chatLogDto {
    // 버스 관련 정보
    String roomId;
    String licenseNo;
    String routeId;
    String createdDate;

    // 채팅로그
    List<logDto> chatLogs;

}
