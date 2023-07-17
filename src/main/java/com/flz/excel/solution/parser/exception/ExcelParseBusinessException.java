package com.flz.excel.solution.parser.exception;

import com.flz.excel.solution.parser.enums.ExcelParseExceptionLevel;
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
}
