package org.cfc.redm.framework.exception;

public class BusinessException extends RuntimeException {

    /**
     * 错误枚举
     */
    private final BusinessError error;

    public BusinessException(BusinessError error) {
        super(error.getMessage());
        this.error = error;
    }

    public BusinessError getError() {
        return error;
    }
}
