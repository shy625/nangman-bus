package com.nangman.redis5.service;

import com.nangman.db.entity.Bus;
import com.nangman.redis5.dto.chatLogDto;
import com.nangman.redis5.dto.chattingRoomDto;
import com.nangman.redis5.dto.roomUserDto;

import java.util.List;

public interface RedisService {
    // 자동으로 되는거
    // 1. updateBusData
    void updateBudData(String sessionId, Bus bus);

    // 2. createChattingRoom
    void createChattingRoom(String sessionId, Bus bus, List<String> busStop);
    // 3. deleteChattingRoom
    chatLogDto deleteChattingRoom(String sessionId);


    // 요청 받는거
    // RestAPI
    // 1. selectRooms(입장 가능한 채팅팡 리스트 조회)
    List<chattingRoomDto> selectRooms(double lat, double lng);
    // 2. checkInRoom(채팅방 접속 가능 여부 확인)
    boolean isAccessibleRoom(double lat, double lng, String sessionId);



    // Socket
    // 1. busStopNow(현재 정류장 위치 조회)
    // 2. upLike(좋아요 등록)
    void upLike(String sessionId, String chatId);
    // 3. downLike(좋아요 취소)
    void downLike(String sessionId, String chatId);
    // 4. 사용자 감정상태 조회?
    // 5. updateMyEmotion(사용자 감정 상태 수정)
    void updateMyEmotion(String sessionId, String userId, int emotion);
    // 6. 사용자 목록 조회?
    List<roomUserDto> roomUserList(String sessionId);
    // 7. createChat(채팅 메시지 전송)
    void createChat(String sessionId, String userId, String chatId, String CreatedTime, String chat);
    // 3. joinRoom(채팅방 입장)
    void joinRoom(String sessionId, String userId);
    // 4. exitRoom(채팅방 퇴장)
    void exitRoom(String sessionId, String userId);
    // 하차정류장 설정
    void setOutBusStop(String sessionId, String userId, String outBusStop);
}
