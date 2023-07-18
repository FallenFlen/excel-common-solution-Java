package com.flz.excel.solution.service.parser.handler;

import com.flz.excel.solution.service.parser.context.ExcelParserContext;

public interface RowBusinessHandler<T extends ExcelParserContext> {
    void handle(T context);
}
