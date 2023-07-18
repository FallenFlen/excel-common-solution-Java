package com.flz.excel.solution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseImportResponseDTO<T> {
    protected Integer successLineCount;
    protected Integer warningLineCount;
    protected String warningMessage;
    protected List<T> data;
}
