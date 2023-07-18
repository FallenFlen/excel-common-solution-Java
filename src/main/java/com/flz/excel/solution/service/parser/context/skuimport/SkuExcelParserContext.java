package com.flz.excel.solution.service.parser.context.skuimport;

import com.flz.excel.solution.dto.parse.skuimport.SkuImportDTO;
import com.flz.excel.solution.dto.parse.skuimport.SkuImportResponseDTO;
import com.flz.excel.solution.service.parser.context.AbstractCommonExcelParserContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkuExcelParserContext extends AbstractCommonExcelParserContext<SkuImportDTO> {
    private SkuImportResponseDTO skuImportResponseDTO;
    private Map<String, String> spuMap = new HashMap<>();
    private Map<String, String> storageMap = new HashMap<>();
}
