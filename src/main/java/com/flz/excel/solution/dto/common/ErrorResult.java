package com.flz.excel.solution.dto.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ErrorResult {
    private int status;
    private String message;
}

