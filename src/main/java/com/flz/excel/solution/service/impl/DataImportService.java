package com.flz.excel.solution.service.impl;

import com.flz.excel.solution.dto.parse.BaseImportResponseDTO;
import com.flz.excel.solution.dto.parse.DataImportRequestDTO;
import com.flz.excel.solution.enums.DataImportType;
import com.flz.excel.solution.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DataImportService {
    private final SkuImportService skuImportService;

    public BaseImportResponseDTO importExcel(DataImportRequestDTO requestDTO) throws IOException {
        DataImportType type = requestDTO.getType();
        BaseImportResponseDTO responseDTO;
        switch (type) {
            case SKU:
                responseDTO = skuImportService.importSku(requestDTO.getFile().getInputStream());
                break;
            default:
                throw new BusinessException("Can not import excel with type:" + type);
        }
        return responseDTO;
    }
}
