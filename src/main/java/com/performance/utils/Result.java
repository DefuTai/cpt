package com.performance.utils;

/**
 * 创建时间: 2019/4/10 下午5:37
 * 类描述:
 *
 * @author lianyu
 */
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    Result() {
        super();
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result ==> {" +
                "code=" + code +
                ", msg=" + msg +
                ", data=" + data +
                "}";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
