package com.nangman.api.controller;

import com.nangman.api.dto.ChatDto;
import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "채팅로그 저장용 Api", tags = {"chat"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor

public class ChatController {
    ChatService chatService;

    @PostMapping("chat/backup")
    @ApiOperation(value = "채팅 내역 저장 + 낭만보고서 생성",
            notes = "방 폭파 후 해당 채팅방의 채팅 내역을 DB에 저장 및 낭만보고서 데이터 생성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    ResponseEntity<?> backup(@RequestBody ChatDto.ChatLog chatLog){
        chatService.InsertChatLogs(chatLog);
    }
}
