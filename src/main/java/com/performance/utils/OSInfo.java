package com.performance.utils;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-13 23:11
 * @Version 1.0
 **/
public class OSInfo {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static OSInfo _instance = new OSInfo();
    private EPlatform platform;

    public static boolean isLinux() {
        return OS.indexOf("linux") >= 0;
    }

    public static boolean isDigitalUnix() {
        return OS.indexOf("digital") >= 0 && OS.indexOf("unix") > 0;
    }

    public static boolean isMacOSX() {
        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
    }

    public static boolean isWindows() {
        return OS.indexOf("windows") >= 0;
    }

    public static EPlatform getOSName() {
        if (isLinux()) {
            _instance.platform = EPlatform.Linux;
        } else if (isDigitalUnix()) {
            _instance.platform = EPlatform.Digital_Unix;
        } else if (isMacOSX()) {
            _instance.platform = EPlatform.Mac_OS_X;
        } else if (isWindows()) {
            _instance.platform = EPlatform.Windows;
        }
        return _instance.platform;
    }

}
