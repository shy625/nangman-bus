package com.nangman.socket;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDto> chatRoomDtoMap;

    @PostConstruct
    private void init(){
        chatRoomDtoMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRooms(){
        System.out.println("ChatRoomRepository - findAllRooms");
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDto> result = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDto findRoomById(String id){
        System.out.println("ChatRoomRepository - findRoomById");
        return chatRoomDtoMap.get(id);
    }

    public ChatRoomDto createChatRoomDTO(String name){
        System.out.println("ChatRoomRepository - createChatRoomDTO");
        ChatRoomDto room = ChatRoomDto.create(name);
        chatRoomDtoMap.put(room.getRoomId(), room);

        return room;
    }
}
