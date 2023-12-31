package com.flz.excel.solution.service.impl;

import com.flz.excel.solution.dto.parse.ImportRow;
import com.flz.excel.solution.dto.parse.skuimport.SkuImportDTO;
import com.flz.excel.solution.dto.parse.skuimport.SkuImportResponseDTO;
import com.flz.excel.solution.service.parser.business.skuimport.SkuImportBusinessParser;
import com.flz.excel.solution.service.parser.context.skuimport.SkuExcelParserContext;
import com.flz.excel.solution.service.parser.file.ExcelFileParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkuImportService {
    private final ExcelFileParser<SkuImportDTO> excelFileParser;

    public SkuImportResponseDTO importSku(InputStream inputStream) {
        SkuExcelParserContext context = new SkuExcelParserContext();
        excelFileParser.batchParse(inputStream,
                importRows -> doImport(context, importRows),
                SkuImportDTO.class,
                1);
        return context.getSkuImportResponseDTO();
    }

    private void doImport(SkuExcelParserContext context, List<ImportRow<SkuImportDTO>> importRows) {
        context.setRows(importRows);
        new SkuImportBusinessParser(context).generateResult();
    }
}
