package com.performance.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-12 12:11
 * @Version 1.0
 **/
public class ConstAdb {

    private static final String ADB_PATH_FOR_MAC = "./src/main/resources/adb/Mac/adb ";
    private static final String ADB_PATH_FOR_UNIX = "./src/main/resources/adb/Unix/adb ";
    private static final String ADB_PATH_FOR_WIN = "./src/main/resources/adb/Win/adb.exe ";

    private static String getAdbPath() {
        switch (OSInfo.getOSName()) {
            case Digital_Unix:
                return ADB_PATH_FOR_UNIX + "%s";
            case Mac_OS_X:
                return ADB_PATH_FOR_MAC + "%s";
            case Windows:
                return ADB_PATH_FOR_WIN + "%s";
            default:
                return null;
        }
    }

    //指定设备（同时连接多台设备时，拼接在adb后面）
    private static final String DESIGNATED_DEVICE = "-s %s:5555 ";

    //获取设备列表
    private static final String DEVICES = getAdbPath() + "devices";
    //通过adb连接设备
    private static final String CONNECT = getAdbPath() + "connect ";
    //获取设备信息
    private static final String PRODUCT_INFO = getAdbPath() + "shell cat /system/build.prop | grep product";
    //获取设备系统版本
    private static final String PRODUCT_SYSTEM_VERSION = getAdbPath() + "shell getprop ro.build.version.release";
    //获取设备型号
    private static final String PRODUCT_SYSTEM_API_VERSION = getAdbPath() + "shell getprop ro.build.version.sdk";
    //获取设备型号
    private static final String PRODUCT_MODEL = getAdbPath() + "shell getprop ro.product.model";
    //获取设备厂商
    private static final String PRODUCT_BRAND = getAdbPath() + "shell getprop ro.product.brand";
    //获取设备序列号
    private static final String SERIALNO = getAdbPath() + "shell getprop ro.serialno";
    //获取设备mac地址
    private static final String MAC_ADDRESS = getAdbPath() + "shell cat /sys/class/net/wlan0/address";
    //获取设备内存信息
    private static final String DEVICES_MEMINFO = getAdbPath() + "shell cat /proc/meminfo";
    //获取设备分辨率
    private static final String RESOLUTION = getAdbPath() + "shell dumpsys window | grep mUnrestrictedScreen";

    public static final String getDevices() {
        return DEVICES;
    }

    public static final String getConnect(String ip) {
        return CONNECT + ip + ":5555";
    }

    public static String getDesignatedDevice(String ip) {
        return StringUtils.isNotEmpty(ip) ? String.format(DESIGNATED_DEVICE, ip) : "";
    }

    public static String getProductInfo(String ip) {
        return String.format(PRODUCT_INFO, getDesignatedDevice(ip));
    }

    public static String getProductSystemVersion(String ip) {
        return String.format(PRODUCT_SYSTEM_VERSION, getDesignatedDevice(ip));
    }

    public static String getProductSystemApiVersion(String ip) {
        return String.format(PRODUCT_SYSTEM_API_VERSION, getDesignatedDevice(ip));
    }

    public static String getProductModel(String ip) {
        return String.format(PRODUCT_MODEL, getDesignatedDevice(ip));
    }

    public static String getProductBrand(String ip) {
        return String.format(PRODUCT_BRAND, getDesignatedDevice(ip));
    }

    public static String getSERIALNO(String ip) {
        return String.format(SERIALNO, getDesignatedDevice(ip));
    }

    public static String getMacAddress(String ip) {
        return String.format(MAC_ADDRESS, getDesignatedDevice(ip));
    }

    public static String getDevicesMeminfo(String ip) {
        return String.format(DEVICES_MEMINFO, getDesignatedDevice(ip));
    }

    public static String getRESOLUTION(String ip) {
        return String.format(RESOLUTION, getDesignatedDevice(ip));
    }

}
