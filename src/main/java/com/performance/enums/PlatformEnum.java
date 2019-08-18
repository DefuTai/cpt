package com.performance.enums;

/**
 * 描述：平台类型
 *
 * @Author 鲢鱼
 * @Data 2019-08-18 11:20
 * @Version 1.0
 **/
public enum  PlatformEnum {

    Linux("Linux"),
    Digital_Unix("Digital Unix"),
    Mac_OS_X("Mac OS X"),
    Windows("Windows");

    private String description;

    PlatformEnum(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }

}
