package com.flz.excel.solution.context;

import com.flz.excel.solution.dto.SkuImportDTO;
import com.flz.excel.solution.dto.SkuImportResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkuExcelParserContext extends AbstractCommonExcelParserContext<SkuImportDTO> {
    private SkuImportResponseDTO skuImportResponseDTO;
}
