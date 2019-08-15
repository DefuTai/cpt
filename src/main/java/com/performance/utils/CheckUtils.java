package com.performance.utils;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-15 18:29
 * @Version 1.0
 **/
public class CheckUtils {

    /**
     * IP地址校验
     *
     * @param ip
     * @return
     */
    public static boolean ipCheck(String ip) {
        if (ip != null && !ip.isEmpty()) {
            // 定义正则表达式
            String regex = "^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$";
            if (ip.matches(regex)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
