package com.nangman.api.controller;

import com.nangman.api.dto.ChatInOutRecordDto;
import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.ChatInOutRecordService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Api(value = "채팅룸 입장/퇴장 로깅 API", tags = {"chatInOutRecord"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ChatInOutRecordController {
    private final ChatInOutRecordService chatInOutRecordService;

    @PostMapping("chatinoutrecords/in")
    @ApiOperation(value = "보고서 리스트 조회", notes = "유저가 참가한 채팅의 낭만보고서들을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<ChatInOutRecordDto.Info> in(@RequestBody
                                                          @ApiParam(value = "roomid가 담긴 body", required = true)
                                                          ChatInOutRecordDto.Request request,
                                                      HttpSession session) {
        return new ResponseEntity<ChatInOutRecordDto.Info>(
                chatInOutRecordService.insertInRecord(
                        new ChatInOutRecordDto.ServiceRequest(request.getRoomId(), Long.parseLong(session.getId()))),
                        HttpStatus.OK);
    }

    @PutMapping("chatinoutrecords/out")
    @ApiOperation(value = "보고서 리스트 조회", notes = "유저가 참가한 채팅의 낭만보고서들을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "입장 기록 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<ChatInOutRecordDto.Info> out(@RequestBody
                                                           @ApiParam(value = "roomid가 담긴 body", required = true)
                                                           ChatInOutRecordDto.Request request,
                                                       HttpSession session) {
        return new ResponseEntity<ChatInOutRecordDto.Info>(
                chatInOutRecordService.insertOutRecord(
                        new ChatInOutRecordDto.ServiceRequest(request.getRoomId(), Long.parseLong(session.getId()))),
                HttpStatus.OK);
    }
}
