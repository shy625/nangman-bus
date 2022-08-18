package com.nangman.api.controller;

import com.nangman.api.dto.ReportDto;
import com.nangman.api.dto.RouteDto;
import com.nangman.api.service.RouteService;
import com.nangman.db.entity.Route;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "노선 추적 API", tags = {"Route"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("route/")
    @ApiOperation(value = "노선 추가", notes = "추적 할 버스 노선을 DB에 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "해당 노선 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<RouteDto.Info> addRoute(@RequestBody @ApiParam(value = "추가하고자 하는 버스의 노선번호와 도시이름", required = true)
                                              RouteDto.Request request) {
        return new ResponseEntity<RouteDto.Info>(
                routeService.followBus(request), HttpStatus.OK);
    }


    @DeleteMapping("route/")
    @ApiOperation(value = "노선 삭제", notes = "추적 할 버스 노선을 DB에서 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "해당 노선 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> DelRoute(@RequestBody @ApiParam(value = "삭제하고자 하는 버스의 노선번호와 도시이름", required = true)
                                               RouteDto.Request request) {
        return new ResponseEntity<String>(
                routeService.unfollowBus(request), HttpStatus.OK);
    }


    @GetMapping("route/detail/{code}")
    @ApiOperation(value = "노선 상세 조회", notes = "선택한 노선의 상세 정보 및 정류소")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "해당 노선 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<RouteDto.Info> getDetail(@PathVariable @ApiParam(value = "노선코드", required = true) String code) {
        return new ResponseEntity<RouteDto.Info>(
                routeService.getRoute(code), HttpStatus.OK);
    }

    @GetMapping("route/")
    @ApiOperation(value = "노선 목록 전체 조회", notes = "추적 할 버스 노선들 가져오긔")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = ReportDto.class),
            @ApiResponse(code = 404, message = "해당 노선 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<RouteDto.Info>> getAllRoutes() {
        return new ResponseEntity<List<RouteDto.Info>>(
                routeService.getAll(), HttpStatus.OK);
    }
}
