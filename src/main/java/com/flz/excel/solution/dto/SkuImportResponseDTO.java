package com.flz.excel.solution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkuImportResponseDTO extends BaseImportResponseDTO<SkuImportResponseDTO.Row> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Row {
        private String code;
        private String spuCode;
        private String name;
        private BigDecimal price;
        private Integer count;
        private String storageId;
    }
}
