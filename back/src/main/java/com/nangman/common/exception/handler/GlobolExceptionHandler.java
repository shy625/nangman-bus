package com.nangman.common.exception.handler;


import com.nangman.api.dto.ErrorDto;
import com.nangman.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import lombok.extern.slf4j.Slf4j;

import static com.nangman.common.constants.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.nangman.common.constants.ErrorCode.INVALID_PARAMETER;

@RestControllerAdvice
@Slf4j
public class GlobolExceptionHandler {

    // Custom Exception Handler
    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<ErrorDto> handleCustomException(CustomException e) {
        log.error("error : " + e.getErrorCode().getMessage());
        e.printStackTrace();

        return new ResponseEntity<ErrorDto>(new ErrorDto(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()),
                HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    // Server Error or Undefined Exception Handler
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<ErrorDto> handleServerException(Exception e) {
        log.error("Server error : " + e.getMessage());
        e.printStackTrace();

        return new ResponseEntity<ErrorDto>(
                new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @Valid : Dto validation Exception
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorDto> processValidationError(MethodArgumentNotValidException e) {
        log.error("error : " + e.getMessage());
        e.printStackTrace();

        return new ResponseEntity<ErrorDto>(new ErrorDto(INVALID_PARAMETER.getStatus(), INVALID_PARAMETER.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}