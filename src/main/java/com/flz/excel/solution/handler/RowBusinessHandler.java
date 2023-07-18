package com.flz.excel.solution.handler;

import com.flz.excel.solution.context.ExcelParserContext;

public interface RowBusinessHandler<T extends ExcelParserContext> {
    void handle(T context);
}
