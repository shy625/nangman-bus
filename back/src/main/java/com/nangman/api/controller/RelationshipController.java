package com.nangman.api.controller;

import com.nangman.api.dto.RelationshipDto;
import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.RelationshipService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api(value = "상대방과의 관계 API", tags = {"Relationship"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class RelationshipController {
    private final RelationshipService relationshipService;

    @GetMapping("relationship/{userId}/{sessionId}/{targetId}")
    @ApiOperation(value = "상대방과의 관계 데이터 조회",
            notes = "상대방과의 관계 데이터 리턴(나와 함께 버스탄 적, 나와 함께한 시간, 상대방의 현재 버스 탑승시간")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "상대 유저 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })

    public ResponseEntity<RelationshipDto.Info> getRelationship(
            @PathVariable @ApiParam(value = "조회하는 유저의 id", required = true) long userId,
            @PathVariable @ApiParam(value = "요청이 온 방의 sessionId", required = true) String sessionId,
            @PathVariable @ApiParam(value = "조회 대상이 된 유저의 id", required = true) long targetId) {
        RelationshipDto.Request request = new RelationshipDto.Request(sessionId, targetId);
        return new ResponseEntity<RelationshipDto.Info>(
                relationshipService.createRelationShip(userId, request.getTargetId(), request.getSessionId()), HttpStatus.OK);
    }
}
