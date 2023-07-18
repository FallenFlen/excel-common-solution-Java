package com.flz.excel.solution.context;

import com.flz.excel.solution.dto.ImportRow;
import com.flz.excel.solution.exception.ExcelParseBusinessException;

import java.util.List;
import java.util.Map;

public interface ExcelParserContext<T> {
    List<ImportRow<T>> getAllRows();

    List<ImportRow<T>> getRowsWithoutException();

    List<ExcelParseBusinessException> getAllExceptions();

    // 行-该行的所有异常
    Map<Integer, List<ExcelParseBusinessException>> getRowAndExceptionsMap();

    void addExceptions(List<ExcelParseBusinessException> exceptions);

    boolean hasError();

    boolean hasWarning();

    String getWarningInfo();

    int getWarningLineCount();

    int getSuccessCount();

}
