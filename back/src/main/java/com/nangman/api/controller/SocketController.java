package com.nangman.api.controller;

import com.nangman.api.dto.ChatInOutRecordDto;
import com.nangman.api.dto.SocketDto;
import com.nangman.api.service.ChatInOutRecordService;
import com.nangman.redis5.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SocketController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final RedisService redisService;
    private final ChatInOutRecordService chatInOutRecordService;

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
    public void enterChatRoom(@DestinationVariable String sessionId, String userId) {
        chatInOutRecordService.insertInRecord(new ChatInOutRecordDto.ServiceRequest(sessionId, Long.parseLong(userId)));
        SocketDto.ChatUser chatUserDto = new SocketDto.ChatUser(userId, 1);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/user", chatUserDto);
    }

    // 채팅 퇴장
    @MessageMapping("chat/rooms/{sessionId}/leave")
    public void leaveChatRoom(@DestinationVariable String sessionId, String userId) {
        chatInOutRecordService.insertOutRecord(new ChatInOutRecordDto.ServiceRequest(sessionId, Long.parseLong(userId)));
        SocketDto.ChatUser chatUserDto = new SocketDto.ChatUser(userId, 2);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/user", chatUserDto);
    }

    // 채팅 - 일반
    @MessageMapping("/chat/rooms/{sessionId}/message")
    public void sendChatMessage(@DestinationVariable String sessionId, SocketDto.ChatPub chatPubDto) {
        String createdTime = LocalDateTime.now().toString();
        String chatId = redisService.createChat(sessionId, chatPubDto.getUserId(), createdTime, chatPubDto.getMessage());
        SocketDto.ChatSub chatSubDto = new SocketDto.ChatSub(chatId, chatPubDto.getUserId(), chatPubDto.getMessage(), createdTime);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/message", chatSubDto);
    }

    // 채팅 좋아요 등록
    @MessageMapping("/chat/rooms/{sessionId}/{chatId}/like/up")
    public void registerChatLike(@DestinationVariable String sessionId, @DestinationVariable String chatId) {
        redisService.upLike(sessionId, chatId);
        int likeCount = redisService.getLike(sessionId, chatId);
        SocketDto.ChatLike chatLikeDto = new SocketDto.ChatLike(chatId, likeCount);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/like", chatLikeDto);
    }

    // 채팅 좋아요 취소
    @MessageMapping("/chat/rooms/{sessionId}/like/down")
    public void cancelChatLike(@DestinationVariable String sessionId, @DestinationVariable String chatId) {
        redisService.downLike(sessionId, chatId);
        int likeCount = redisService.getLike(sessionId, chatId);
        SocketDto.ChatLike chatLikeDto = new SocketDto.ChatLike(chatId, likeCount);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/like", chatLikeDto);
    }

    // 귓속말 설정

    // 현재 정류장 위치

    // 사용자 감정 상태 조회

}
