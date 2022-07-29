package com.nangman.api.controller;

import com.nangman.api.dto.ReportDto;
import com.nangman.api.dto.UserDto;
import com.nangman.api.service.ReportService;
import com.nangman.db.entity.Report;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(value = "낭만보고서 API", tags = {"report"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping("reports")
    @ApiOperation(value = "보고서 리스트 조회", notes = "유저가 참가한 채팅의 낭만보고서들을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "보고서 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Report>> getReportsByUserId(@RequestBody @ApiParam(value = "낭만보고서 목록을 조회할 유저 아이디", required = true) long userId) {
        return new ResponseEntity<List<Report>>(reportService.getReportsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("reports/detail")
    @ApiOperation(value = "보고서 리스트 조회", notes = "유저가 참가한 채팅의 낭만보고서들을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "보고서 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<ReportDto.Info> getReportById(@RequestBody @ApiParam(value = "상세 조회 할 보고서의 아이디와 유저 아이디", required = true) ReportDto.DetailRequest detailRequest) {
        ReportDto.Info reportInfo = reportService.getReportByIds(detailRequest);
        if (reportInfo == null) return new ResponseEntity<ReportDto.Info>(reportInfo, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<ReportDto.Info>(reportInfo, HttpStatus.OK);
    }
}
