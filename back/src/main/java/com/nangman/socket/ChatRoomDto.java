package com.nangman.socket;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class ChatRoomDto {

    private String roomId;
    private String roomName;
    private Set<WebSocketSession> sessionSet;

    public static ChatRoomDto create(String roomName) {
        ChatRoomDto room = new ChatRoomDto();

        room.roomId = UUID.randomUUID().toString();
        room.roomName = roomName;
        room.sessionSet = new HashSet<>();

        return room;
    }

}
