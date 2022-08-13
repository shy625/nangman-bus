package com.nangman.socket;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "채팅 API", tags = {"Chat"})
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class RoomController {

    private final ChatRoomRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public List<ChatRoomDto> rooms(){
        System.out.println("RoomController - rooms");
        return repository.findAllRooms();
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public ChatRoomDto create(@RequestParam String name){
        System.out.println("RoomController - create");
        return repository.createChatRoomDTO(name);
    }
}
