package com.nangman.api.controller;

import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.ReportService;
import com.nangman.db.entity.Report;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Slf4j
//@Api(value = "메인화면 데이터 API", tags = {"main"})
//@RestController
//@RequestMapping("api/")
//@RequiredArgsConstructor
//public class MainController {
//
//    @GetMapping("main/top/bus")
//    @ApiOperation(value = "보고서 리스트 조회", notes = "유저가 참가한 채팅의 낭만보고서들을 리턴")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
//            @ApiResponse(code = 404, message = "보고서 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
////    public ResponseEntity<List<Report>> loadMain(HttpSession session) {
////        return new ResponseEntity<List<Report>>(
////                reportService.getReportsByUserId(Long.parseLong(session.getId())), HttpStatus.OK);
////    }
//}
