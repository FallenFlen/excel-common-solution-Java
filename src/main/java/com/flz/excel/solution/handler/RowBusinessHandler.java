package com.flz.excel.solution.handler;

import com.flz.excel.solution.context.ExcelParserContext;

public interface RowBusinessHandler<T extends ExcelParserContext<T>> {
    void handle(T context);
}
