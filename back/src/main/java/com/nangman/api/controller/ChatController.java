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

}
