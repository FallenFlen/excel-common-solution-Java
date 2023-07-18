package com.flz.excel.solution.controller;

import com.flz.excel.solution.dto.parse.BaseImportResponseDTO;
import com.flz.excel.solution.dto.parse.DataImportRequestDTO;
import com.flz.excel.solution.service.impl.DataImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data/import")
public class DataImportController {
    private final DataImportService dataImportService;

    @PostMapping("/excel")
    public BaseImportResponseDTO importExcel(DataImportRequestDTO requestDTO) throws IOException {
        return dataImportService.importExcel(requestDTO);
    }
}
