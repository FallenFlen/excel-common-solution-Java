package com.flz.excel.solution.service.parser.business;

import com.flz.excel.solution.dto.parse.BaseImportResponseDTO;
import com.flz.excel.solution.exception.BusinessException;
import com.flz.excel.solution.service.parser.context.ExcelParserContext;
import com.flz.excel.solution.service.parser.handler.RowBusinessHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public abstract class AbstractExcelBusinessParser<T extends ExcelParserContext, R extends BaseImportResponseDTO> {
    protected List<RowBusinessHandler<T>> handlers;
    protected T context;

    // 生成结果：执行整条责任链，并直接调用generateResultDirectly
    public R generateResult() {
        for (RowBusinessHandler<T> handler : handlers) {
            handler.handle(context);
        }

        String parserName = getParserName();
        if (context.hasWarning()) {
            String warningInfo = context.getWarningInfo();
            log.warn("[{}] There are {} warnings during parsing:\n{}", parserName, context.getWarningLineCount(), warningInfo);
        }
        // 如果有Error级别的异常，直接报错
        if (context.hasError()) {
            String errorInfo = context.getErrorInfo();
            log.error("[{}] There are {} errors during parsing:\n{}", parserName, context.getErrorLineCount(), errorInfo);
            throw new BusinessException(String.format("[%s] There are errors during parsing:%s", parserName, errorInfo));
        }

        log.info("[{}] There are {} lines imported successfully", parserName, context.getSuccessLineCount());
        return generateResultDirectly();
    }

    // 子类实现真正的构建返回结果的逻辑
    public abstract R generateResultDirectly();

    public abstract String getParserName();

}
