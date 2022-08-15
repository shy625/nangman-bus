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
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SocketController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final RedisService redisService;
    private final ChatInOutRecordService chatInOutRecordService;

    // 채팅 입장
    @MessageMapping("/chat/rooms/{sessionId}/in")
    public void enterChatRoom(@DestinationVariable String sessionId, SocketDto.PubUserInOut pubUserInOutDto) {
        Long userId = pubUserInOutDto.getUserId();
        String message = pubUserInOutDto.getMessage();
        chatInOutRecordService.insertInRecord(new ChatInOutRecordDto.ServiceRequest(sessionId, userId));
        redisService.joinRoom(sessionId, userId);
        SocketDto.SubUserInOut subUserInOutDto = new SocketDto.SubUserInOut(userId, 1, message);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/user", subUserInOutDto);
    }

    // 채팅 퇴장
    @MessageMapping("/chat/rooms/{sessionId}/out")
    public void leaveChatRoom(@DestinationVariable String sessionId, SocketDto.PubUserInOut pubUserInOutDto) {
        Long userId = pubUserInOutDto.getUserId();
        chatInOutRecordService.insertOutRecord(new ChatInOutRecordDto.ServiceRequest(sessionId, userId));
        redisService.exitRoom(sessionId, userId);
        SocketDto.SubUserInOut subUserInOutDto = new SocketDto.SubUserInOut(userId, 2, null);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/user", subUserInOutDto);
    }

    // 채팅 - 일반
    @MessageMapping("/chat/rooms/{sessionId}/message")
    public void sendChatMessage(@DestinationVariable String sessionId, SocketDto.PubChat pubChatDto) {
        log.info("sendChatMessage() ChatPub - userId : " + pubChatDto.getUserId() + " message : " + pubChatDto.getMessage());
        String createdTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String chatId = redisService.createChat(sessionId, String.valueOf(pubChatDto.getUserId()), createdTime, pubChatDto.getMessage());
        SocketDto.SubChat subChatDto = new SocketDto.SubChat(Long.valueOf(chatId), pubChatDto.getUserId(), pubChatDto.getMessage(), createdTime);
        log.info("sendChatMessage() ChatSub - userId : " + subChatDto.getUserId() + " message : " + subChatDto.getMessage());
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/message", subChatDto);
    }

    // 채팅 좋아요 등록
    @MessageMapping("/chat/rooms/{sessionId}/{chatId}/like/up")
    public void registerChatLike(@DestinationVariable String sessionId, @DestinationVariable Long chatId) {
        redisService.upLike(sessionId, String.valueOf(chatId));
        int likeCount = redisService.getLike(sessionId, String.valueOf(chatId));
        SocketDto.ChatLike chatLikeDto = new SocketDto.ChatLike(chatId, likeCount);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/like", chatLikeDto);
    }

    // 채팅 좋아요 취소
    @MessageMapping("/chat/rooms/{sessionId}/{chatId}/like/down")
    public void cancelChatLike(@DestinationVariable String sessionId, @DestinationVariable Long chatId) {
        redisService.downLike(sessionId, String.valueOf(chatId));
        int likeCount = redisService.getLike(sessionId, String.valueOf(chatId));
        SocketDto.ChatLike chatLikeDto = new SocketDto.ChatLike(chatId, likeCount);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/like", chatLikeDto);
    }

    // 버스가 현재 위치한 정류장 업데이트
    public void sendCurrentBusStop(String sessionId, Long busStopId, String busStopName) {
        SocketDto.ChatBusStop chatBusStopDto = new SocketDto.ChatBusStop(busStopId, busStopName);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/busStop", chatBusStopDto);
    }

    // 사용자 감정 상태 설정 - 0 : 무표정, 1 : 화나요, 2 : 기분좋아요, 3 : 우울해요
    @MessageMapping("/chat/rooms/{sessionId}/{userId}/emotion")
    public void setUserEmotion(@DestinationVariable String sessionId, @DestinationVariable Long userId, int emotion) {
        redisService.updateMyEmotion(sessionId, String.valueOf(userId), emotion);
        SocketDto.ChatUserEmotion chatUserEmotionDto = new SocketDto.ChatUserEmotion(userId, emotion);
        template.convertAndSend("/sub/chat/rooms/" + sessionId + "/emotion", chatUserEmotionDto);
    }

    // 사용자 하차 정류장 설정

    // 귓속말 설정

}
