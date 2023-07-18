package com.flz.excel.solution.parser.handler.skuimport;

import com.flz.excel.solution.dto.parse.ImportRow;
import com.flz.excel.solution.dto.parse.skuimport.SkuImportDTO;
import com.flz.excel.solution.exception.ExcelParseBusinessException;
import com.flz.excel.solution.parser.context.skuimport.SkuExcelParserContext;
import com.flz.excel.solution.parser.handler.RowBusinessHandler;
import com.flz.excel.solution.repository.SpuRepository;
import com.flz.excel.solution.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SkuValidationHandler implements RowBusinessHandler<SkuExcelParserContext> {
    private final SpuRepository spuRepository;
    private final StorageRepository storageRepository;

    @Override
    public void handle(SkuExcelParserContext context) {
        Map<String, String> spuMap = spuRepository.findAll();
        Map<String, String> storageMap = storageRepository.findAll();
        List<ImportRow<SkuImportDTO>> rows = context.getAllRows();
        rows.forEach(row -> {
            Integer lineNum = row.getLineNum();
            // 校验spu是否存在
            String spuCode = row.getData().getSpuCode();
            if (spuMap.containsKey(spuCode)) {
                context.getSpuMap().put(spuCode, spuMap.get(spuCode));
            } else {
                context.addExceptions(List.of(ExcelParseBusinessException.error("spu不存在", lineNum)));
            }

            // 校验库存是否存在
            String storageId = row.getData().getStorageId();
            if (storageMap.containsKey(storageId)) {
                context.getStorageMap().put(storageId, storageMap.get(storageId));
            } else {
                context.addExceptions(List.of(ExcelParseBusinessException.warn("库存不存在，等待调配", lineNum)));
            }
        });
    }
}
