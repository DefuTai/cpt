package com.performance.utils;

import com.performance.enums.PlatformEnum;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-13 23:11
 * @Version 1.0
 **/
public class OSInfoUtil {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static OSInfoUtil _instance = new OSInfoUtil();
    private PlatformEnum platform;

    private static boolean isLinux() {
        return OS.indexOf("linux") >= 0;
    }

    private static boolean isDigitalUnix() {
        return OS.indexOf("digital") >= 0 && OS.indexOf("unix") > 0;
    }

    private static boolean isMacOSX() {
        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
    }

    private static boolean isWindows() {
        return OS.indexOf("windows") >= 0;
    }

    public static PlatformEnum getOSName() {
        if (isLinux()) {
            _instance.platform = PlatformEnum.Linux;
        } else if (isDigitalUnix()) {
            _instance.platform = PlatformEnum.Digital_Unix;
        } else if (isMacOSX()) {
            _instance.platform = PlatformEnum.Mac_OS_X;
        } else if (isWindows()) {
            _instance.platform = PlatformEnum.Windows;
        }
        return _instance.platform;
    }

}
