package com.performance.enums;

/**
 * 创建时间: 2019/4/10 下午5:26
 * 类描述:
 *
 * @author lianyu
 */
public enum ResultEnum {

    SUCCESS(1000, "成功"),
    ERROR_UNKNOWN(-1, "未知错误"),
    ERROR_IS_NULL(0, "结果为空"),
    ERROR_LACK_BUSINESS_PARAMETERS(2001, "缺少业务参数");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
