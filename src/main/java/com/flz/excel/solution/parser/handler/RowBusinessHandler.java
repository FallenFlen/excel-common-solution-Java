package com.flz.excel.solution.parser.handler;

import com.flz.excel.solution.parser.context.ExcelParserContext;

public interface RowBusinessHandler<T extends ExcelParserContext> {
    void handle(T context);
}
