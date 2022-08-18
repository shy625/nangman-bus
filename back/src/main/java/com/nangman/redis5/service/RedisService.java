package com.nangman.redis5.service;

import com.nangman.api.dto.BusStopDto;
import com.nangman.api.dto.ChatDto;
import com.nangman.db.entity.Bus;
import com.nangman.redis5.dto.ChattingRoomDto;
import com.nangman.redis5.dto.RandomBusDto;
import com.nangman.redis5.dto.RoomUserDto;

import java.util.List;

public interface RedisService {
    // 자동으로 되는거
    // 1. updateBusData
    void updateBudData(Bus bus);
    public void setBUS_CHECK_DIST(double dist);

    // 2. createChattingRoom
    void createChattingRoom(Bus bus);
    // 3. deleteChattingRoom
    ChatDto.ChatLog deleteChattingRoom(String sessionId);


    // 요청 받는거
    // RestAPI
    // 1. selectRooms(입장 가능한 채팅팡 리스트 조회)
    List<ChattingRoomDto.ListInfo> selectRooms(double lat, double lng);
    // 2. checkInRoom(채팅방 접속 가능 여부 확인)
    boolean isAccessibleRoom(double lat, double lng, String sessionId);
    // 3. 메인에서 랜덤으로 버스 3개 받는거
    List<RandomBusDto> getRandomBus();
    // TODO : 채팅방 입장 시 이전 채팅 기록 + 버스 노선 정류장 리스트 + 현재 사용자 목록 전달


    ChatDto.ChatLog getChattingLog(String sessionId);

    List<BusStopDto.Info> getBusStops(String sessionId);

    List<RoomUserDto> roomUserList(String sessionId);


    // Socket
    // 1. busStopNow(현재 정류장 위치 조회)
    // 2. upLike(좋아요 등록)
    void upLike(String sessionId, Long chatId);

    // 3. downLike(좋아요 취소)
    void downLike(String sessionId, Long chatId);

    int getLike(String sessionId, Long chatId);

    // 4. 사용자 감정상태 조회?
    // 5. updateMyEmotion(사용자 감정 상태 수정)
    // emotion 규칙 정해야됨 0: 무표정 1: 화나요 2: 기분좋아요 3: 우울해요
    void updateMyEmotion(String sessionId, String userId, int emotion);

    // 7. createChat(채팅 메시지 전송)
    String createChat(String sessionId, Long userId, String chat, String createdTime);

    // 3. joinRoom(채팅방 입장)
    void joinRoom(String sessionId, Long userId);

    // 4. exitRoom(채팅방 퇴장)
    void exitRoom(String sessionId, Long userId);

    // 하차정류장 설정
    void setOutBusStop(String sessionId, Long userId, Long busStopId);

    List<String> getSessionList();
}
