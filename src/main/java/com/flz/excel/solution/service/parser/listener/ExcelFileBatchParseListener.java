package com.flz.excel.solution.service.parser.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.flz.excel.solution.dto.parse.ImportRow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ExcelFileBatchParseListener<T> implements ReadListener<T> {
    private int batchSize;
    private List<ImportRow<T>> dataList;
    private Consumer<List<ImportRow<T>>> handler;
    private int headRowNumber;

    public ExcelFileBatchParseListener(int batchSize, int headRowNumber, Consumer<List<ImportRow<T>>> handler) {
        this.batchSize = batchSize;
        this.handler = handler;
        this.headRowNumber = headRowNumber;
        this.dataList = new ArrayList<>();
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        int rowIndex = context.readRowHolder().getRowIndex() + headRowNumber - 1;
        dataList.add(new ImportRow<>(rowIndex, data));
        // 如果列表大小大于等于批量大小，说明该处理本批了，然后存储下一批的数据
        if (dataList.size() >= batchSize) {
            handler.accept(dataList);
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!dataList.isEmpty()) {
            handler.accept(dataList);
            dataList.clear();
        }
    }
}
