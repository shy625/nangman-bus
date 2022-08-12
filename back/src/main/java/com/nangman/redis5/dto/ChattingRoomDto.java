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
    long busId;
    String sessionId;
    String routeId;
    int distance;
    int inUsers;
    int type;
}
