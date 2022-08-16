package com.nangman.redis5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomUserDto {

    private Long userId;
    private String nickName;
    private Boolean isTodayBirth;
    private Integer emotion;
    private Long outBusStopId;
}
