package com.flz.excel.solution.parser.business.skuimport;

import com.flz.excel.solution.context.skuimport.SkuExcelParserContext;
import com.flz.excel.solution.dto.skuimport.SkuImportResponseDTO;
import com.flz.excel.solution.parser.business.ExcelBusinessParser;

public class SkuImportBusinessParser extends ExcelBusinessParser<SkuExcelParserContext, SkuImportResponseDTO> {

    @Override
    public SkuImportResponseDTO generateResultDirectly() {
        return null;
    }

    @Override
    public String getParserName() {
        return "SkuImportBusinessParser";
    }
}
