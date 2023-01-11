package org.cfc.redm.framework.exception;

import org.cfc.redm.commons.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> businessError(BusinessException e) {
        System.out.println(e.getMessage());
        return new Result<>().setCode(e.getError().getCode()).setMessage(e.getError().getMessage());
    }
}
