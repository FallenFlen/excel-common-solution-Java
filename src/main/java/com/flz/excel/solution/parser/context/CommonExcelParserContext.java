package com.flz.excel.solution.parser.context;

import com.flz.excel.solution.parser.exception.ExcelParseBusinessException;
import com.flz.excel.solution.parser.row.ImportRow;

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
public class CommonExcelParserContext<T> implements ExcelParserContext<T> {
    private List<ImportRow<T>> rows = new ArrayList<>();
    private Map<Integer, List<ExcelParseBusinessException>> exceptionMap = new HashMap<>();

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
}
