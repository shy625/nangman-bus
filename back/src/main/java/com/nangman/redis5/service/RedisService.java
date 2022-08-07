package com.nangman.redis5.service;

public interface RedisService {
    // 자동으로 되는거
    // 1. updateBusData
    // 2. createChattingRoom
    // 3. deleteChattingRoom


    // 요청 받는거
    // RestAPI
    // 1. selectRooms(입장 가능한 채팅팡 리스트 조회)
    // 2. checkInRoom(채팅방 접속 가능 여부 확인)
    // 3. joinRoom(채팅방 입장)
    // 4. exitRoom(채팅방 퇴장)


    // Socket
    // 1. busStopNow(현재 정류장 위치 조회)
    // 2. upLike(좋아요 등록)
    // 3. downLike(좋아요 취소)
    // 4. 사용자 감정상태 조회?
    // 5. updateMyEmotion(사용자 감정 상태 수정)
    // 6. 사용자 목록 조회?
    // 7. createChat(채팅 메시지 전송)
}
