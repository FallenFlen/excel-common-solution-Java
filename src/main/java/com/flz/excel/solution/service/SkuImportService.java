package com.flz.excel.solution.service;

import com.flz.excel.solution.context.skuimport.SkuExcelParserContext;
import com.flz.excel.solution.dto.ImportRow;
import com.flz.excel.solution.dto.skuimport.SkuImportDTO;
import com.flz.excel.solution.dto.skuimport.SkuImportResponseDTO;
import com.flz.excel.solution.parser.business.skuimport.SkuImportBusinessParser;
import com.flz.excel.solution.parser.read.file.ExcelFileParser;
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
