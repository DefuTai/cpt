//package com.performance.utils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 描述：
// *
// * @Author 鲢鱼
// * @Data 2019-08-12 12:11
// * @Version 1.0
// **/
//@Deprecated
//public class ConstAdb {
//
//    private static final Logger logger = LoggerFactory.getLogger(ConstAdb.class);
//
//    private static Process process;
//    private static BufferedReader br;
//    private static String Result;
//
//    private static final String ADB_PATH_FOR_MAC = "./src/main/resources/adb/Mac/adb ";
//    private static final String ADB_PATH_FOR_UNIX = "./src/main/resources/adb/Unix/adb ";
//    private static final String ADB_PATH_FOR_WIN = "./src/main/resources/adb/Win/adb.exe ";
//
//    private static String getAdbPath() {
//        switch (OSInfo.getOSName()) {
//            case Digital_Unix:
//                return ADB_PATH_FOR_UNIX + "%s";
//            case Mac_OS_X:
//                return ADB_PATH_FOR_MAC + "%s";
//            case Windows:
//                return ADB_PATH_FOR_WIN + "%s";
//            default:
//                return null;
//        }
//    }
//
//    //重启adb服务进程
//    private static final String START_SERVER = getAdbPath() + "start-server";
//    //终止adb服务进程
//    private static final String KILL_SERVER = getAdbPath() + "kill-server";
//
//    //重启设备
//    private static final String REBOOT = getAdbPath() + "reboot";
//    //获取设备列表
//    private static final String DEVICES = getAdbPath() + "devices";
//    //通过adb连接设备
//    private static final String CONNECT = getAdbPath() + "connect ";
//    //判断设备状态
//    private static final String STATE = getAdbPath() + "get-state";
//
//    //获取设备信息
//    private static final String PRODUCT_INFO = getAdbPath() + "shell cat /system/build.prop | grep product";
//    //获取设备系统版本
//    private static final String PRODUCT_SYSTEM_VERSION = getAdbPath() + "shell getprop ro.build.version.release";
//    //获取设备系统api版本
//    private static final String PRODUCT_SYSTEM_API_VERSION = getAdbPath() + "shell getprop ro.build.version.sdk";
//    //获取设备型号
//    private static final String PRODUCT_MODEL = getAdbPath() + "shell getprop ro.product.model";
//    //获取设备厂商
//    private static final String PRODUCT_BRAND = getAdbPath() + "shell getprop ro.product.brand";
//    //获取设备序列号
//    private static final String SERIALNO = getAdbPath() + "shell getprop ro.serialno";
//    //获取设备mac地址
//    private static final String MAC_ADDRESS = getAdbPath() + "shell cat /sys/class/net/wlan0/address";
//    //获取IP地址
//    private static final String IP = getAdbPath() + "shell getprop dhcp.wlan0.ipaddress";
//
//    //获取设备分辨率
//    //private static final String RESOLUTION = getAdbPath() + "shell dumpsys window | grep mUnrestrictedScreen";
//    private static final String RESOLUTION = getAdbPath() + "shell wm size";
//    //获取设备内存信息
//    private static final String MEMINFO = getAdbPath() + "shell cat /proc/meminfo";
//    //获取设备CPU最小频率
//    private static final String CPUINFO_MIN_FREQ = getAdbPath() + "shell cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
//    //获取设备CPU最大频率
//    private static final String CPUINFO_MAX_FREQ = getAdbPath() + "shell cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
//
//    //列出目录下的文件和文件夹
//    private static final String LS_FOLDER_LIST = getAdbPath() + "shell ls /sys/devices/system/cpu";
//
//    /******************************************************************************************************************/
//    /*************************************************** PUBLIC方法 ****************************************************/
//    /******************************************************************************************************************/
//
//    /**
//     * 重启adb服务
//     */
//    public static void getStartServer() {
//        try {
//            process = Runtime.getRuntime().exec(String.format(REBOOT, splitSerialNumber(START_SERVER)));
//        } catch (IOException e) {
//            logger.error("重启adb服务异常：", e);
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 终止adb服务
//     */
//    public static void getKillServer() {
//        try {
//            process = Runtime.getRuntime().exec(String.format(REBOOT, splitSerialNumber(KILL_SERVER)));
//        } catch (IOException e) {
//            logger.error("终止adb服务异常：", e);
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 重启设备
//     *
//     * @param sn 设备标识（ip和序列号都行）
//     */
//    public static void getReboot(String sn) {
//        try {
//            process = Runtime.getRuntime().exec(String.format(REBOOT, splitSerialNumber(sn)));
//        } catch (IOException e) {
//            logger.error("重启设备异常：", e);
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * adb连接远程设备
//     *
//     * @param ip
//     * @return
//     */
//    public static final String getConnect(String ip) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            process = Runtime.getRuntime().exec(String.format(CONNECT, "") + ip + ":5555");
//            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
//            while (StringUtils.isNotEmpty(Result = br.readLine())) {
//                sb.append(Result + "\n");
//            }
//        } catch (IOException e) {
//            logger.error("adb connect命令执行异常：", e);
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 获取设备列表
//     *
//     * @return
//     */
//    public static Map getDevices() {
//        Map<String, String> deviceMap = new HashMap<>();
//        try {
//            process = Runtime.getRuntime().exec(String.format(DEVICES, ""));
//            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
//            while (StringUtils.isNotEmpty(Result = br.readLine())) {
//                if (Result.contains("daemon")) {
//                    continue;
//                } else if (Result.contains("List of devices")) {
//                    continue;
//                } else {
//                    String[] var = Result.split("\\t");
//                    deviceMap.put(var[0], var[1]);
//                }
//            }
//        } catch (IOException e) {
//            logger.error("adb命令执行异常：", e);
//        } catch (Exception e) {
//            logger.error("获取设备列表异常：", e);
//        }
//        return deviceMap;
//    }
//
//    /**
//     * 判断设备状态
//     *
//     * @return
//     */
//    public static String getSTATE(String sn) {
//        return getDeviceInfo(STATE, sn);
//    }
//
//    /**
//     * 获取设备信息
//     *
//     * @param sn
//     * @return
//     */
//    public static String getProductInfo(String sn) {
//        return getDeviceInfo(PRODUCT_INFO, sn);
//    }
//
//    /**
//     * 获取设备系统版本
//     *
//     * @param sn
//     * @return
//     */
//    public static String getProductSystemVersion(String sn) {
//        return getDeviceInfo(PRODUCT_SYSTEM_VERSION, sn);
//    }
//
//    /**
//     * 获取设备系统api版本
//     *
//     * @param sn
//     * @return
//     */
//    public static String getProductSystemApiVersion(String sn) {
//        return getDeviceInfo(PRODUCT_SYSTEM_API_VERSION, sn);
//    }
//
//    /**
//     * 获取设备型号
//     *
//     * @param sn
//     * @return
//     */
//    public static String getProductModel(String sn) {
//        return getDeviceInfo(PRODUCT_MODEL, sn);
//    }
//
//    /**
//     * 获取设备厂商
//     *
//     * @param sn
//     * @return
//     */
//    public static String getProductBrand(String sn) {
//        return getDeviceInfo(PRODUCT_BRAND, sn);
//    }
//
//    /**
//     * 获取设备序列号
//     *
//     * @param sn
//     * @return
//     */
//    public static String getSERIALNO(String sn) {
//        return getDeviceInfo(SERIALNO, sn);
//    }
//
//    /**
//     * 获取设备mac地址
//     *
//     * @param sn
//     * @return
//     */
//    public static String getMacAddress(String sn) {
//        return getDeviceInfo(MAC_ADDRESS, sn);
//    }
//
//    /**
//     * 获取IP地址
//     *
//     * @param sn
//     * @return
//     */
//    public static String getIp(String sn) {
//        return getDeviceInfo(IP, sn);
//    }
//
//    /**
//     * 获取设备内存信息
//     *
//     * @param sn
//     * @return
//     */
//    public static String getMeminfo(String sn) {
//        return getDeviceInfo(MEMINFO, sn);
//    }
//
//    /**
//     * 获取设备分辨率
//     *
//     * @param sn
//     * @return
//     */
//    public static String getResolution(String sn) {
//        return getDeviceInfo(RESOLUTION, sn);
//    }
//
//    /**
//     * 获取设备CPU最小频率
//     *
//     * @param sn
//     * @return
//     */
//    public static String getCpuinfoMinFreq(String sn) {
//        return getDeviceInfo(CPUINFO_MIN_FREQ, sn);
//    }
//
//    /**
//     * 获取设备CPU最大频率
//     *
//     * @param sn
//     * @return
//     */
//    public static String getCpuinfoMaxFreq(String sn) {
//        return getDeviceInfo(CPUINFO_MAX_FREQ, sn);
//    }
//
//    /**
//     * 获取CPU核数
//     *
//     * @param sn
//     * @return
//     */
//    public static int getCpuCore(String sn) {
//        int core = 0;
//        try {
//            process = Runtime.getRuntime().exec(String.format(LS_FOLDER_LIST, splitSerialNumber(sn)));
//            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
//            String regex = "^cpu\\d{1,2}$";
//            while (StringUtils.isNotEmpty(Result = br.readLine())) {
//                if (Result.matches(regex)) {
//                    core++;
//                }
//            }
//        } catch (IOException e) {
//            logger.error("adb命令执行异常：", e);
//        }
//        return core;
//    }
//
//    /******************************************************************************************************************/
//    /************************************************** PRIVATE方法 ***************************************************/
//    /******************************************************************************************************************/
//
//    /**
//     * 执行adb命令，并返回执行结果
//     *
//     * @param adbCommand adb命令
//     * @param sn   设备标识（ip、序列号均可）
//     * @return
//     */
//    private static String getDeviceInfo(String adbCommand, String sn) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            process = Runtime.getRuntime().exec(String.format(adbCommand, splitSerialNumber(sn)));
//            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
//            while (StringUtils.isNotEmpty(Result = br.readLine())) {
//                sb.append(Result + "\n");
//            }
//        } catch (IOException e) {
//            logger.error("adb命令执行异常：", e);
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 指定目标设备（"-s ip:port" or "-s 序列号"）
//     *
//     * @param sn serialNumber
//     * @return
//     */
//    private static String splitSerialNumber(String sn) {
//        if (sn.isEmpty()) {
//            return "";
//        } else if (CheckUtils.ipCheck(sn)) {
//            return String.format("-s %s:5555 ", sn);
//        } else {
//            return String.format("-s %s ", sn);
//        }
//    }
//
//    public static void main(String[] args) {
//        System.out.println(getSTATE("10.1.130.59"));
//        System.out.println(getSTATE("CNDFP616120500002895"));
//    }
//
//}
