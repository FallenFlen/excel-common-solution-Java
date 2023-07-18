package com.flz.excel.solution.dto.parse;

import com.flz.excel.solution.enums.DataImportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataImportRequestDTO {
    private DataImportType type;
    private MultipartFile file;
}
