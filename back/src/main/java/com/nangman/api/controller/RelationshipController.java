package com.nangman.api.controller;

import com.nangman.api.dto.RelationshipDto;
import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.RelationshipService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@Api(value = "상대방과의 관계 API", tags = {"Relationship"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class RelationshipController {
    private final RelationshipService relationshipService;

    @GetMapping("relationship")
    @ApiOperation(value = "상대방과의 관계 데이터 조회",
            notes = "상대방과의 관계 데이터 리턴(나와 함께 버스탄 적, 나와 함께한 시간, 상대방의 현재 버스 탑승시간")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "상대 유저 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })

    public ResponseEntity<RelationshipDto.Info> getRelationship(HttpSession session,
                                                                @RequestBody @ApiParam(value = "상대 유저 정보", required = true) RelationshipDto.Request request) {
        return new ResponseEntity<RelationshipDto.Info>(
                relationshipService.createRelationShip(Long.parseLong(session.getId()), request.getUserId(), request.getSessionId()), HttpStatus.OK);
    }
}
