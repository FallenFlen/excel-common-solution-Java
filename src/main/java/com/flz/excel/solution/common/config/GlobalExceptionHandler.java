package com.flz.excel.solution.common.config;

import com.flz.excel.solution.dto.common.ErrorResult;
import com.flz.excel.solution.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult handleBusinessException(BusinessException e) {
        e.printStackTrace();
        return ErrorResult.of(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult handleOtherException(Exception e) {
        e.printStackTrace();
        return ErrorResult.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
