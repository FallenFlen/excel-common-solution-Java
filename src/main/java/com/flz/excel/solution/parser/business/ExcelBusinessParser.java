package com.flz.excel.solution.parser.business;

import com.flz.excel.solution.context.ExcelParserContext;
import com.flz.excel.solution.dto.BaseImportResponseDTO;
import com.flz.excel.solution.enums.ExcelParseExceptionLevel;
import com.flz.excel.solution.exception.BusinessException;
import com.flz.excel.solution.exception.ExcelParseBusinessException;
import com.flz.excel.solution.handler.RowBusinessHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class ExcelBusinessParser<T extends ExcelParserContext, R extends BaseImportResponseDTO> {
    protected List<RowBusinessHandler<T>> handlers;
    protected T context;

    // 生成结果：执行整条责任链，并直接generateResultDirectly
    public R generateResult() {
        for (RowBusinessHandler<T> handler : handlers) {
            handler.handle(context);
        }

        String parserName = getParserName();
        if (context.hasWarning()) {
            String warningInfo = buildTips(ExcelParseExceptionLevel.WARNING);
            context.setWarningInfo(warningInfo);
            log.warn("[{}] There are warnings during parsing:{}", parserName, warningInfo);
        }
        // 如果有Error级别的异常，直接报错
        if (context.hasError()) {
            String errorInfo = buildTips(ExcelParseExceptionLevel.ERROR);
            log.warn("[{}] There are errors during parsing:{}", parserName, errorInfo);
            throw new BusinessException(String.format("[%s] There are errors during parsing:%s", parserName, errorInfo));
        }

        return generateResultDirectly();
    }

    // 子类实现真正的构建返回结果的逻辑
    public abstract R generateResultDirectly();

    public abstract String getParserName();

    // 构建提示信息
    private String buildTips(ExcelParseExceptionLevel level) {
        Map<Integer, List<ExcelParseBusinessException>> rowAndExceptionsMap = context.getRowAndExceptionsMap();
        return rowAndExceptionsMap.values().stream()
                .map(it -> buildSingleLineTips((List<ExcelParseBusinessException>) it, level))
                .collect(Collectors.joining("\n"));
    }

    private String buildSingleLineTips(List<ExcelParseBusinessException> exceptions, ExcelParseExceptionLevel level) {
        if (CollectionUtils.isEmpty(exceptions)) {
            return "";
        }

        return exceptions.stream()
                .filter(it -> it.getLevel() == level)
                .map(it -> String.format("%s | Line %s - %s", level.name(), it.getLineNum(), it.getErrorCode()))
                .collect(Collectors.joining("\n"));

    }

}
