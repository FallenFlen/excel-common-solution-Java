package com.flz.excel.solution.dto.parse.skuimport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkuImportDTO {
    @NotBlank(message = "Sku编码不能为空")
    private String code;
    @NotBlank(message = "Sku编码不能为空")
    private String spuCode;
    @NotBlank(message = "Sku编码不能为空")
    private String name;
    @NotNull(message = "价格不能为空")
    @Positive(message = "价格必须为正数")
    private BigDecimal price;
    @NotNull(message = "数量不能为空")
    @Positive(message = "数量必须为正数")
    private Integer count;
    private String storageId;
}
