package com.nangman.redis5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomDto {
    private long busId;
    private String sessionId;
    private String routeId;
    private int distance;
    private int inUsers;
    private int type;
}
