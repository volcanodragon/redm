package org.cfc.redm.commons.result;

/**
 * 统一返回结果对象
 */
public class Result <T> {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回描述
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public Result() {}

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 成功
     *
     * @return Result<T>
     */
    public Result<T> success() {
        this.setCode(ResultCode.SUCCESS.getCode()).setMessage(ResultCode.SUCCESS.getMessage());
        return this;
    }

    /**
     * 失败
     *
     * @return Result<T>
     */
    public Result<T> fail() {
        this.setCode(ResultCode.FAIL.getCode()).setMessage(ResultCode.FAIL.getMessage());
        return this;
    }
}
