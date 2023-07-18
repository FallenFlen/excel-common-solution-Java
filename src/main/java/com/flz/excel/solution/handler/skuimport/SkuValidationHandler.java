package com.flz.excel.solution.handler.skuimport;

import com.flz.excel.solution.context.ExcelParserContext;
import com.flz.excel.solution.dto.ImportRow;
import com.flz.excel.solution.dto.skuimport.SkuImportDTO;
import com.flz.excel.solution.handler.RowBusinessHandler;

import java.util.List;

public class SkuValidationHandler implements RowBusinessHandler<ExcelParserContext<SkuImportDTO>> {
    @Override
    public void handle(ExcelParserContext<SkuImportDTO> context) {
        // 校验spu是否存在，模拟查db
        List<ImportRow<SkuImportDTO>> rows = context.getAllRows();

    }
}
