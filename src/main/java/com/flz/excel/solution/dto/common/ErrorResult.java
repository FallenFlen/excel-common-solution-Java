package com.flz.excel.solution.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResult {
    private int status;
    private String errorCode;
    private String message;

    public static ErrorResult of(int status, String errorCode, String message) {
        return new ErrorResult(status, errorCode, message);
    }

    public static ErrorResult of(int status, String message) {
        return new ErrorResult(status, null, message);
    }
}

