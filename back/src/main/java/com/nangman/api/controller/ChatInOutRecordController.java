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
@Api(value = "채팅룸 입장/퇴장 로깅 API", tags = {"ChatInOutRecord"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ChatInOutRecordController {
    private final ChatInOutRecordService chatInOutRecordService;

    @PostMapping("chatinoutrecords/in")
    @ApiOperation(value = "입장 시간 기록", notes = "채팅방 입장시 유저의 입장을 기록")
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
                        new ChatInOutRecordDto.ServiceRequest(request.getSessionId(), Long.parseLong(session.getId()))),
                HttpStatus.OK);
    }

    @PutMapping("chatinoutrecords/out")
    @ApiOperation(value = "퇴장 시간 기록", notes = "채팅방 퇴장시 유저의 퇴장을 기록")
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
                        new ChatInOutRecordDto.ServiceRequest(request.getSessionId(), Long.parseLong(session.getId()))),
                HttpStatus.OK);
    }
}