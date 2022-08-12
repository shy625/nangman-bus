package com.nangman.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "잘못된 파라미터 값 전송"),

    //404 NOT_FOUND 잘못된 리소스 접근
    BUS_NOT_FOUND(404, "존재하지 않는 버스 또는 노선 정보"),
    REPORT_NOT_FOUND(404, "존재하지 않는 낭만 보고서 ID"),
    USER_NOT_FOUND(404, "존재하지 않는 사용자 ID"),
    ROUTE_NOT_FOUND(404, "존재하지 않는 버스노선"),

    REPORT_INFO_NOT_MATCHED(404, "전송된 정보와 일치 하지 않는 DB상 정보"),

    //409 CONFLICT 중복된 리소스
    USER_ALREADY_EXISTED(409, "이미 저장되어 있는 첨부사진 ID"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러");


    private final int status;
    private final String message;

}