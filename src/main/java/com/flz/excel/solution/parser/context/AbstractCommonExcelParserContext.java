package com.flz.excel.solution.parser.context;

import com.flz.excel.solution.dto.parse.ImportRow;
import com.flz.excel.solution.enums.ExcelParseExceptionLevel;
import com.flz.excel.solution.exception.ExcelParseBusinessException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

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

    @Override
    public List<ImportRow<T>> getAllRows() {
        return rows;
    }

    @Override
    public List<ImportRow<T>> getRowsWithoutException() {
        Set<Integer> errorRows = exceptionMap.entrySet().stream()
                // 只有错误当作导入失败，警告可当作导入成功
                .filter(it -> it.getValue().stream().allMatch(it1 -> it1.getLevel() == ExcelParseExceptionLevel.ERROR))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return rows.stream()
                .filter(it -> !errorRows.contains(it.getLineNum()))
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
        return buildTips(ExcelParseExceptionLevel.WARNING);
    }

    @Override
    public String getErrorInfo() {
        return buildTips(ExcelParseExceptionLevel.ERROR);
    }

    @Override
    public int getWarningLineCount() {
        return getExceptionCountBy(ExcelParseExceptionLevel.WARNING);
    }

    @Override
    public int getSuccessLineCount() {
        return getRowsWithoutException().size();
    }

    @Override
    public int getErrorLineCount() {
        return getExceptionCountBy(ExcelParseExceptionLevel.ERROR);
    }

    private int getExceptionCountBy(ExcelParseExceptionLevel level) {
        return (int) exceptionMap.values().stream()
                .flatMap(List::stream)
                .filter(it -> it.getLevel() == level)
                .count();
    }

    // 构建提示信息
    private String buildTips(ExcelParseExceptionLevel level) {
        return getRowAndExceptionsMap().values().stream()
                .map(it -> buildSingleLineTip(it, level))
                .collect(Collectors.joining("\n"));
    }

    private String buildSingleLineTip(List<ExcelParseBusinessException> exceptions, ExcelParseExceptionLevel level) {
        if (CollectionUtils.isEmpty(exceptions)) {
            return "";
        }

        return exceptions.stream()
                .filter(it -> it.getLevel() == level)
                .map(it -> String.format("%s | Line %s - %s", level.name(), it.getLineNum(), it.getMessage()))
                .collect(Collectors.joining("\n"));

    }

    public void setRows(List<ImportRow<T>> rows) {
        this.rows = rows;
    }
}
