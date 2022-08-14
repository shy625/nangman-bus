package com.nangman.redis5.dto;

import com.nangman.api.dto.BusStopDto;
import com.nangman.api.dto.ChatDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ChattingRoomDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListInfo {
        private long busId;
        private String sessionId;
        private String routeId;
        private int distance;
        private int inUsers;
        private int type;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private ChatDto.ChatLog chatRoomInfo;
        private List<RoomUserDto> roomUserInfoList;
        private List<BusStopDto.Info> busStopInfoList;
    }
}
