package com.nangman.api.controller;

import com.nangman.api.dto.MainDto;
import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.MainService;
import com.nangman.db.entity.User;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@Api(value = "메인화면 데이터 API", tags = {"Main"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("main/topbus")
    @ApiOperation(value = "메인 데이터 조회", notes = "유저가 참가한 채팅의 낭만보고서들을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "보고서 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MainDto.Info> loadMain(HttpSession session) {
        log.info(session);
        User user = (User) session.getAttribute("User");
        if (user == null) log.info("user is null..");

        log.info("==================" + user.getId().toString());
        return new ResponseEntity<MainDto.Info>(
                mainService.getMainPageData(user.getId()), HttpStatus.OK);
    }
}
