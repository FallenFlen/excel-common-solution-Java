package com.flz.excel.solution.parser.file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.flz.excel.solution.exception.BusinessException;
import com.flz.excel.solution.parser.listener.ExcelFileBatchParseListener;
import com.flz.excel.solution.row.ImportRow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Component
public class ExcelFileParser<T> {
    @Value("${excel.parser.batch-size}")
    private int batchSize;

    public void batchParse(InputStream inputStream, Consumer<List<ImportRow<T>>> handler, Class<T> type) {
        batchParse(inputStream, handler, type, batchSize, 0);
    }

    public void batchParse(InputStream inputStream, Consumer<List<ImportRow<T>>> handler, Class<T> type, int batchSize, int headRowNumber) {
        try {
            ExcelFileBatchParseListener<T> parseListener = new ExcelFileBatchParseListener<>(batchSize, handler);
            EasyExcel.read(inputStream, type, parseListener)
                    .excelType(ExcelTypeEnum.XLSX)
                    .autoCloseStream(true)
                    .ignoreEmptyRow(true)
                    .autoTrim(true)
                    .headRowNumber(headRowNumber) // 表头占用的行数，会跳过表头来解析
                    .sheet()
                    .doRead();
        } catch (Exception e) {
            log.error("parse excel failed:", e);
            throw new BusinessException("parse excel failed");
        }
    }
}