package com.flz.excel.solution.dto.parse.skuimport;

import com.flz.excel.solution.dto.parse.BaseImportResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class SkuImportResponseDTO extends BaseImportResponseDTO<SkuImportResponseDTO.Row> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Row {
        private String code;
        private String spuCode;
        private String spuName;
        private String name;
        private BigDecimal price;
        private Integer count;
        private String storageId;
        private String storageName;
    }
}
