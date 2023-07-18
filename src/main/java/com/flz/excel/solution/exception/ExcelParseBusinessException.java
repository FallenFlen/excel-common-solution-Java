package com.flz.excel.solution.exception;

import com.flz.excel.solution.enums.ExcelParseExceptionLevel;
import lombok.Getter;

@Getter
public class ExcelParseBusinessException extends BusinessException {
    private Integer lineNum;
    private ExcelParseExceptionLevel level;

    public ExcelParseBusinessException(String message, Integer lineNum, ExcelParseExceptionLevel level) {
        super(message);
        this.lineNum = lineNum;
        this.level = level;
    }

    public static ExcelParseBusinessException warn(String message, Integer lineNum) {
        return new ExcelParseBusinessException(message, lineNum, ExcelParseExceptionLevel.WARNING);
    }

    public static ExcelParseBusinessException error(String message, Integer lineNum) {
        return new ExcelParseBusinessException(message, lineNum, ExcelParseExceptionLevel.ERROR);
    }
}
