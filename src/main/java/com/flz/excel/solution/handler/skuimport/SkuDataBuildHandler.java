package com.flz.excel.solution.handler.skuimport;

import com.flz.excel.solution.context.skuimport.SkuExcelParserContext;
import com.flz.excel.solution.dto.ImportRow;
import com.flz.excel.solution.dto.skuimport.SkuImportResponseDTO;
import com.flz.excel.solution.handler.RowBusinessHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SkuDataBuildHandler implements RowBusinessHandler<SkuExcelParserContext> {

    @Override
    public void handle(SkuExcelParserContext context) {
        // 最后一步，构建我们需要的数据
        SkuImportResponseDTO skuImportResponseDTO = new SkuImportResponseDTO();
        skuImportResponseDTO.setSuccessLineCount(context.getSuccessLineCount());
        skuImportResponseDTO.setWarningLineCount(context.getWarningLineCount());
        skuImportResponseDTO.setWarningMessage(context.getWarningInfo());
        Map<String, String> spuMap = context.getSpuMap();
        Map<String, String> storageMap = context.getStorageMap();

        List<SkuImportResponseDTO.Row> rows = context.getRowsWithoutException().stream()
                .map(ImportRow::getData)
                .map(it -> SkuImportResponseDTO.Row.builder()
                        .code(it.getCode())
                        .spuCode(it.getSpuCode())
                        .spuName(spuMap.get(it.getSpuCode())) // spu name从上下文中获取
                        .count(it.getCount())
                        .name(it.getName())
                        .price(it.getPrice())
                        .storageId(it.getStorageId())
                        .storageName(storageMap.get(it.getStorageId())) // 库存name同理
                        .build())
                .collect(Collectors.toList());
        skuImportResponseDTO.setData(rows);

        context.setSkuImportResponseDTO(skuImportResponseDTO);
    }
}
