package com.flz.excel.solution.parser.business.skuimport;

import com.flz.excel.solution.common.utils.SpringApplicationContextUtils;
import com.flz.excel.solution.context.skuimport.SkuExcelParserContext;
import com.flz.excel.solution.dto.skuimport.SkuImportResponseDTO;
import com.flz.excel.solution.handler.BaseValidationHandler;
import com.flz.excel.solution.handler.skuimport.SkuDataBuildHandler;
import com.flz.excel.solution.handler.skuimport.SkuValidationHandler;
import com.flz.excel.solution.parser.business.AbstractExcelBusinessParser;

import java.util.List;

/**
 * sku导入，业务解析器，用于组装本次导入实现的校验器，组装成链条
 * 这个类不能是一个bean，因为context不能是单例的，每次导入都要创建一个新的context
 */
public class SkuImportBusinessParser extends AbstractExcelBusinessParser<SkuExcelParserContext, SkuImportResponseDTO> {
    public SkuImportBusinessParser(SkuExcelParserContext context) {
        super(List.of(
                SpringApplicationContextUtils.getBean(BaseValidationHandler.class),
                SpringApplicationContextUtils.getBean(SkuValidationHandler.class),
                SpringApplicationContextUtils.getBean(SkuDataBuildHandler.class)
        ), context);
    }

    @Override
    public SkuImportResponseDTO generateResultDirectly() {
        return context.getSkuImportResponseDTO();
    }

    @Override
    public String getParserName() {
        return "SkuImportBusinessParser";
    }
}
