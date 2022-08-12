package com.nangman.api.controller;

import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.ReportService;
import com.nangman.db.entity.Report;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@Api(value = "낭만보고서 API", tags = {"Report"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("reports")
    @ApiOperation(value = "보고서 리스트 조회", notes = "유저가 참가한 채팅의 낭만보고서들을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "보고서 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Report>> getReportsByUserId(HttpSession session) {
        return new ResponseEntity<List<Report>>(
                reportService.getReportsByUserId(Long.parseLong(session.getId())), HttpStatus.OK);
    }

    @GetMapping("reports/detail/{reportId}")
    @ApiOperation(value = "보고서 상세 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "보고서 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<ReportDto.Info> getReportById(
            @PathVariable @ApiParam(value = "보고서 id, url 맨 끝에 전달", required = true)long reportId, HttpSession session){
        return new ResponseEntity<ReportDto.Info>(reportService.getReportByIds(
                new ReportDto.DetailRequest(Long.parseLong(session.getId()), reportId)), HttpStatus.OK);
    }
}
