package com.nangman.api.controller;

import com.nangman.socket.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SocketController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
//    @MessageMapping(value = "/chat/enter")
//    public void enter(ChatMessageDto message){
//        System.out.println("ChatController - enter");
//        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
//        template.convertAndSend("/sub/chat/room/" + message.getSessionId(), message);
//    }
//
//    @MessageMapping(value = "/chat/message")
//    public void message(ChatMessageDto message){
//        System.out.println("ChatController - message");
//        template.convertAndSend("/sub/chat/room/" + message.getSessionId(), message);
//    }

    // 채팅 입장
    @MessageMapping("chat/rooms/{sessionId}/enter")
    public void enterChatRoom(@DestinationVariable String sessionId, ChatMessageDto messageDto) {
        ZoneId z = ZoneId.of("Asia/Seoul");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        messageDto.setCreatedDate(zdt.toString());  // TODO : 프론트 시간 표시 요구사항에 따라 format 변경
        // TODO : Redis 저장 및 inTime 체크
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/enter", messageDto);
    }

    // 채팅 퇴장
    @MessageMapping("chat/rooms/{sessionId}/leave")
    public void leaveChatRoom(@DestinationVariable String sessionId) {
        // TODO : Redis 저장 및 outTime 체크
    }

    // 채팅 - 일반
    @MessageMapping("/chat/rooms/{sessionId}/message")
    public void sendChatMessage(@DestinationVariable String sessionId, ChatMessageDto messageDto) {
        // TODO : Redis 저장
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/message", messageDto);
    }

    // 채팅 좋아요 등록
    @MessageMapping("/chat/rooms/{sessionId}/{chatId}/like/up")
    public void registerChatLike(@DestinationVariable String sessionId, @DestinationVariable String chatId) {
        template.convertAndSend("/sub/chat/rooms/" + sessionId);
    }

    // 채팅 좋아요 취소
    @MessageMapping("/chat/rooms/{sessionId}/like/down")
    public void cancelChatLike() {

    }

    // 귓속말 설정

    // 현재 정류장 위치

    // 사용자 감정 상태 조회

    private String getNow() {
        ZoneId z = ZoneId.of("Asia/Seoul");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        return zdt.toString();
    }

}
