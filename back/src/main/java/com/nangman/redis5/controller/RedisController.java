package com.nangman.redis5.controller;

import com.nangman.api.dto.ChatDto;
import com.nangman.api.service.ChatInOutRecordService;
import com.nangman.api.service.ChatService;
import com.nangman.db.entity.Bus;
import com.nangman.db.repository.BusRepository;
import com.nangman.redis5.dto.*;
import com.nangman.redis5.service.RedisService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/redis")
@Api(value = "레디스 API", tags = {"redis"})
public class RedisController {

    private final RedisService redisService;
    private final BusRepository busRepository;
    private final ChatInOutRecordService chatInOutRecordService;
    private final ChatService chatService;

    @GetMapping("/")
    public String ok() {
        return "ok";
    }

    @GetMapping("/selectRooms/{lat}/{lng}")
    public ResponseEntity<List<ChattingRoomDto.ListInfo>> selectRooms(@PathVariable double lat, @PathVariable double lng) {
        return new ResponseEntity<List<ChattingRoomDto.ListInfo>>(
                redisService.selectRooms(lat, lng),HttpStatus.OK);
    }

    @GetMapping("/getRandomBus")
    public ResponseEntity<List<RandomBusDto>> getRandomBus() {
        return new ResponseEntity<List<RandomBusDto>>(
                redisService.getRandomBus(), HttpStatus.OK);
    }

    @GetMapping("/isAccessibleRoom/{lat}/{lng}/{sessionId}")
    public boolean isAccessibleRoom(@PathVariable double lat, @PathVariable double lng, @PathVariable String sessionId) {
        return true;
//        return redisService.isAccessibleRoom(lat, lng, sessionId);
    }

    @GetMapping("/rooms/{sessionId}")
    public ChattingRoomDto.Info getChatRoomInfo(@PathVariable String sessionId) {
        ChattingRoomDto.Info chatRoomInfoDto = new ChattingRoomDto.Info();
        chatRoomInfoDto.setChatRoomInfo(redisService.getChattingLog(sessionId));
        chatRoomInfoDto.setRoomUserInfoList(redisService.roomUserList(sessionId));
        chatRoomInfoDto.setBusStopInfoList(redisService.getBusStops(sessionId));
        return chatRoomInfoDto;
    }

    @GetMapping("/test/sessions")
    public List<String> getSessionList() {
        return redisService.getSessionList();
    }

    @GetMapping("/test/killBus/{sessionId}")
    public void killBus(String sessionId) {
        Bus bus = busRepository.findBusBySessionId(sessionId).get();
        ChatDto.ChatLog chatLog = redisService.deleteChattingRoom(bus.getSessionId());
        chatInOutRecordService.forceOut(bus.getSessionId());
        chatService.InsertChatLogs(chatLog);
        bus.setSessionId(null);
        busRepository.save(bus);
    }

    @GetMapping("/test/setBusSuchDist/{dist}")
    public void setBusSuchDist(double dist) {
        redisService.setBUS_CHECK_DIST(dist);
    }


//    @GetMapping("/test/upLike")
//    public void upLike() {
//        redisService.upLike(testSessionId, "1");
//    }
//
//    @GetMapping("/test/downLike")
//    public void downLike() {
//        redisService.downLike(testSessionId, "1");
//    }
//
//    @GetMapping("/test/getLike")
//    public long getLike() {
//        return redisService.getLike(testSessionId, "1");
//    }
//
//    @GetMapping("/test/updateMyEmotion")
//    public void updateMyEmotion() {
//        redisService.updateMyEmotion(testSessionId, "1", 1);
//    }
//
//    @GetMapping("/test/roomUserList")
//    public List<RoomUserDto> roomUserList() {
//        return redisService.roomUserList(testSessionId);
////        return ResponseEntity<List<roomUserDto>>(new List<roomUserDto>(redisService.roomUserList(testSessionId)), HttpStatus.OK);
//    }
//
//    @GetMapping("/test/createChat")
//    public void createChat() {
//        redisService.createChat(testSessionId,"6", LocalDateTime.now().toString(), "hello");
//    }
//
//    @GetMapping("/test/joinRoom")
//    public void joinRoom() {
//        RoomUserDto roomUserDto = new RoomUserDto();
//        roomUserDto.setNickName("nickName11");
//        roomUserDto.setBirth("today");
//        roomUserDto.setEmotion(1);
//        roomUserDto.setOutBusStop("3");
//        redisService.joinRoom(testSessionId, roomUserDto);
//    }
//
//    @GetMapping("/test/exitRoom")
//    public void exitRoom() {
//        redisService.exitRoom(testSessionId, "10");
//    }
//
//    @GetMapping("/test/setOutBusStop")
//    public void setOutBusStop() {
//        redisService.setOutBusStop(testSessionId, "10", "1");
//    }
}
