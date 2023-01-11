package org.cfc.redm.framework.exception;

public enum BusinessError {

    USERNAME_REPEAT(70001, "用户名已经存在");

    private final int code;
    private final String message;

    BusinessError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
