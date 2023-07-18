package com.flz.excel.solution.handler.skuimport;

import com.flz.excel.solution.context.ExcelParserContext;
import com.flz.excel.solution.handler.RowBusinessHandler;
import com.flz.excel.solution.row.dto.SkuImportDTO;

public class SkuDataBuildHandler implements RowBusinessHandler<ExcelParserContext<SkuImportDTO>> {
    @Override
    public void handle(ExcelParserContext<SkuImportDTO> context) {
        // 最后一步，构建我们需要的数据
    }
}
