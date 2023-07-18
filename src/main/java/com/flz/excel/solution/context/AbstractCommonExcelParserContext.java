package com.flz.excel.solution.context;

import com.flz.excel.solution.dto.ImportRow;
import com.flz.excel.solution.enums.ExcelParseExceptionLevel;
import com.flz.excel.solution.exception.ExcelParseBusinessException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通用上下文，如果业务不需要在上下文保存其他的数据，用这个
 *
 * @param <T>
 */
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractCommonExcelParserContext<T> implements ExcelParserContext<T> {
    protected List<ImportRow<T>> rows = new ArrayList<>();
    protected Map<Integer, List<ExcelParseBusinessException>> exceptionMap = new HashMap<>();
    protected String warningInfo;

    @Override
    public List<ImportRow<T>> getAllRows() {
        return rows;
    }

    @Override
    public List<ImportRow<T>> getRowsWithoutException() {
        Set<Integer> keySet = exceptionMap.keySet();
        return rows.stream()
                .filter(it -> !keySet.contains(it.getLineNum()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExcelParseBusinessException> getAllExceptions() {
        return exceptionMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<ExcelParseBusinessException>> getRowAndExceptionsMap() {
        return exceptionMap;
    }

    @Override
    public void addExceptions(List<ExcelParseBusinessException> exceptions) {
        exceptions.forEach(e -> exceptionMap.computeIfAbsent(e.getLineNum(), v -> new ArrayList<>()).add(e));
    }

    @Override
    public boolean hasError() {
        return exceptionMap.values().stream()
                .flatMap(List::stream)
                .anyMatch(it -> it.getLevel() == ExcelParseExceptionLevel.ERROR);
    }

    @Override
    public boolean hasWarning() {
        return getWarningLineCount() == 0;
    }

    @Override
    public String getWarningInfo() {
        return warningInfo;
    }

    @Override
    public int getWarningLineCount() {
        return (int) exceptionMap.values().stream()
                .flatMap(List::stream)
                .filter(it -> it.getLevel() == ExcelParseExceptionLevel.WARNING)
                .count();
    }

    @Override
    public int getSuccessCount() {
        return rows.size() - getWarningLineCount();
    }
}
