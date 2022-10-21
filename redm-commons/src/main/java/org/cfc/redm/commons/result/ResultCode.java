package org.cfc.redm.commons.result;

public enum ResultCode {

    SUCCESS(2000, "success"),

    FAIL(5000, "fail"),
    LOGIN_FAIL(5001, "账号或者密码错误"),
    NO_LOGIN(5002, "未登录"),
    ACCESS_DENIED(5003, "无权访问");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
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
