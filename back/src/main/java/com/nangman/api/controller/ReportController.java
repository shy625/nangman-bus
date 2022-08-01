package com.nangman.api.controller;

import com.nangman.api.dto.ReportDto;
import com.nangman.api.service.ReportService;
import com.nangman.db.entity.Report;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;
import retrofit2.http.Path;

@Slf4j
@Api(value = "낭만보고서 API", tags = {"report"})
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
    public ResponseEntity<List<Report>> getReportsByUserId(@RequestBody @ApiParam(value = "낭만보고서 목록을 조회할 유저 아이디", required = true) long userId) {
        return new ResponseEntity<List<Report>>(reportService.getReportsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("reports/detail/{reportId}")
    @ApiOperation(value = "보고서 상세 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "보고서 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<ReportDto.Info> getReportById(@PathVariable @ApiParam(value = "보고서 id, url 맨 끝에 전달", required = true)long reportId,
                                                        @ApiParam(value = "낭만보고서를 조회할 유저 아이디", required = true)long userId) {
        ResponseEntity<ReportDto.Info> result;
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        ReportDto.Info reportInfo = reportService.getReportByIds(new ReportDto.DetailRequest(userId, reportId));
        if (reportInfo != null) statusCode = HttpStatus.OK;
        result = new ResponseEntity<ReportDto.Info>(reportInfo, statusCode);
        return result;
    }
}
