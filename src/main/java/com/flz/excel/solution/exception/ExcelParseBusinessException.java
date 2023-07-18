package com.flz.excel.solution.exception;

import com.flz.excel.solution.enums.ExcelParseExceptionLevel;
import lombok.Getter;

@Getter
public class ExcelParseBusinessException extends BusinessException {
    private Integer lineNum;
    private ExcelParseExceptionLevel level;

    public ExcelParseBusinessException(String errorCode, Integer lineNum, ExcelParseExceptionLevel level) {
        super(errorCode);
        this.lineNum = lineNum;
        this.level = level;
    }

    public static ExcelParseBusinessException warn(String errorCode, Integer lineNum) {
        return new ExcelParseBusinessException(errorCode, lineNum, ExcelParseExceptionLevel.WARNING);
    }

    public static ExcelParseBusinessException error(String errorCode, Integer lineNum) {
        return new ExcelParseBusinessException(errorCode, lineNum, ExcelParseExceptionLevel.ERROR);
    }
}
