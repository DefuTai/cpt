package com.performance.enums;

/**
 * 创建时间: 2019/4/10 下午5:26
 * 类描述:
 *
 * @author lianyu
 */
public enum ResultEnum {

    SUCCESS("1000", "成功"),

    ERROR_UNKNOWN("0000", "系统异常"),
    ERROR_LACK_BUSINESS_PARAMETERS("2001", "缺少业务参数"),

    ERROR_CUSTOM("6666", "自定义异常");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
