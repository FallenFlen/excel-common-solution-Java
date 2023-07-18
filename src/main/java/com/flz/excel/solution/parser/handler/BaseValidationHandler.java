package com.flz.excel.solution.parser.handler;

import com.flz.excel.solution.dto.parse.ImportRow;
import com.flz.excel.solution.exception.ExcelParseBusinessException;
import com.flz.excel.solution.parser.context.ExcelParserContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 基础校验：注解校验
 */
@Component
public class BaseValidationHandler<T> implements RowBusinessHandler<ExcelParserContext<T>> {

    @Override
    public void handle(ExcelParserContext<T> context) {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.getValidator();
            List<ImportRow<T>> rows = context.getAllRows();
            for (ImportRow<T> row : rows) {
                // 校验失败的集合
                Set<ConstraintViolation<T>> constraintViolations = validator.validate(row.getData());
                if (!constraintViolations.isEmpty()) {
                    List<ExcelParseBusinessException> exceptions = constraintViolations.stream()
                            .map(it -> ExcelParseBusinessException.error(it.getMessage(), row.getLineNum())) // error级别
                            .collect(Collectors.toList());
                    context.addExceptions(exceptions);
                }
            }
        }

    }
}
