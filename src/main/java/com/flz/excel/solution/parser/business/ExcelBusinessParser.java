package com.flz.excel.solution.parser.business;

import com.flz.excel.solution.context.ExcelParserContext;
import com.flz.excel.solution.dto.BaseImportResponseDTO;
import com.flz.excel.solution.exception.BusinessException;
import com.flz.excel.solution.handler.RowBusinessHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
            String warningInfo = context.getWarningInfo();
            log.warn("[{}] There are {} warnings during parsing:{}", parserName, context.getWarningLineCount(), warningInfo);
        }
        // 如果有Error级别的异常，直接报错
        if (context.hasError()) {
            String errorInfo = context.getErrorInfo();
            log.error("[{}] There are {} errors during parsing:{}", parserName, context.getErrorLineCount(), errorInfo);
            throw new BusinessException(String.format("[%s] There are errors during parsing:%s", parserName, errorInfo));
        }

        log.info("[{}] There are {} lines imported successfully", parserName, context.getSuccessLineCount());
        return generateResultDirectly();
    }

    // 子类实现真正的构建返回结果的逻辑
    public abstract R generateResultDirectly();

    public abstract String getParserName();

}
