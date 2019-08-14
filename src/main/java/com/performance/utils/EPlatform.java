package com.performance.utils;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-13 23:12
 * @Version 1.0
 **/
public enum EPlatform {

    Linux("Linux"),
    Digital_Unix("Digital Unix"),
    Mac_OS_X("Mac OS X"),
    Windows("Windows");

    EPlatform(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }

    private String description;

}
