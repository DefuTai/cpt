package com.performance.enums;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-30 18:18
 * @Version 1.0
 **/
public enum ConnStatusEnum {

    DEVICE("device", 1),
    OFFLINE("offline", 0),
    NO_DEVICE("no device", -1);

    ConnStatusEnum(String code, Integer value) {
        this.code = code;
        this.value = value;
    }

    private String code;
    private Integer value;

    public String getCode() {
        return code;
    }

    public Integer getValue() {
        return value;
    }

    public static Integer getValueByCode(String code) {
        for (ConnStatusEnum connStatus : ConnStatusEnum.values()) {
            if (code.equals(connStatus.getCode())) {
                return connStatus.getValue();
            }
        }
        return null;
    }
}
