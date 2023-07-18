package com.flz.excel.solution.handler.skuimport;

import com.flz.excel.solution.context.skuimport.SkuExcelParserContext;
import com.flz.excel.solution.dto.skuimport.SkuImportResponseDTO;
import com.flz.excel.solution.handler.RowBusinessHandler;

public class SkuDataBuildHandler implements RowBusinessHandler<SkuExcelParserContext> {

    @Override
    public void handle(SkuExcelParserContext context) {
        // 最后一步，构建我们需要的数据
        SkuImportResponseDTO skuImportResponseDTO = new SkuImportResponseDTO();

        context.setSkuImportResponseDTO(skuImportResponseDTO);
    }
}
