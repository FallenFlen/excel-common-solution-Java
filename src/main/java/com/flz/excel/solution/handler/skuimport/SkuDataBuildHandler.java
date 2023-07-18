package com.flz.excel.solution.handler.skuimport;

import com.flz.excel.solution.context.ExcelParserContext;
import com.flz.excel.solution.dto.SkuImportDTO;
import com.flz.excel.solution.handler.RowBusinessHandler;

public class SkuDataBuildHandler implements RowBusinessHandler<ExcelParserContext<SkuImportDTO>> {
    @Override
    public void handle(ExcelParserContext<SkuImportDTO> context) {
        // 最后一步，构建我们需要的数据
    }
}
