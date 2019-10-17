package com.performance.enums;

/**
 * 创建时间: 2019/4/10 下午5:26
 * 类描述:
 *
 * @author lianyu
 */
public enum ResultEnum {

    SUCCESS("2000", "成功"),
    ERROR_UNKNOWN("0000", "系统异常"),

    ERROR_CUSTOM("4444", "自定义异常"),

    ERROR_LACK_BUSINESS_PARAMETERS("4001", "不能为空"),
    ERROR_EXE_SERVICE_APP_ID_NULL("4002", "请选择关联应用"),
    ERROR_EXE_SERVICE_DEVICE_ID_NULL("4003", "请选择关联设备"),
    ERROR_EXE_SERVICE_CASE_LIST_NULL("4004", "请选择关联用例"),
    ERROR_EXE_SERVICE_APP_NOT_EXIST("4005", "所提交应用ID不存在，请确认后再次提交"),
    ERROR_EXE_SERVICE_DEVICES_NOT_EXIST("4006", "所提交设备ID不存在，请确认后再次提交"),
    ERROR_EXE_SERVICE_CASE_NOT_EXIST("4007", "所提交用例ID不存在，请确认后再次提交"),
    ERROR_EXE_SERVICE_EXPLAN_NOT_EXIST("4008", "执行计划不存在或已被删除，请确认后重试");

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
