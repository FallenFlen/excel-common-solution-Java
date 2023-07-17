package com.flz.excel.solution.parser.row;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class ImportRow<T> {
    private Integer lineNum;
    private T data;

    public static <T> List<ImportRow<T>> generate(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }

        List<ImportRow<T>> rows = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            rows.add(new ImportRow<>(i + 1, data.get(i)));
        }
        return rows;
    }
}
