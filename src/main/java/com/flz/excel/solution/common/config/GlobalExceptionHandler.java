package com.flz.excel.solution.common.config;

import com.flz.excel.solution.dto.common.ErrorResult;
import com.flz.excel.solution.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ErrorResult handleBusinessException(BusinessException e) {
        log.error("BusinessException:", e);
        return ErrorResult.of(HttpStatus.BAD_REQUEST.value(),e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult handleOtherException(Exception e) {
        log.error("Exception:", e);
        return ErrorResult.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
