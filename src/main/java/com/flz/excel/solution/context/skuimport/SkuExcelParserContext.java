package com.flz.excel.solution.context.skuimport;

import com.flz.excel.solution.context.AbstractCommonExcelParserContext;
import com.flz.excel.solution.dto.skuimport.SkuImportDTO;
import com.flz.excel.solution.dto.skuimport.SkuImportResponseDTO;
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
