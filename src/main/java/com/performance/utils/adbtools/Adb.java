package com.performance.utils.adbtools;

import com.performance.utils.CheckUtils;
import com.performance.utils.OSInfoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-16 14:31
 * @Version 1.0
 **/
public class Adb {

    protected static final Logger logger = LoggerFactory.getLogger(Adb.class);

    protected static Process process;
    protected static BufferedReader br;
    protected static String Result;

    protected static final String ADB_PATH_FOR_MAC = "./src/main/resources/adb/Mac/adb ";
    protected static final String ADB_PATH_FOR_UNIX = "./src/main/resources/adb/Unix/adb ";
    protected static final String ADB_PATH_FOR_WIN = "./src/main/resources/adb/Win/adb.exe ";

    private static String getAdbPath() {
        switch (OSInfoUtil.getOSName()) {
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

    //重启adb服务进程
    protected static final String START_SERVER = getAdbPath() + "start-server";
    //终止adb服务进程
    protected static final String KILL_SERVER = getAdbPath() + "kill-server";
    //重启设备
    protected static final String REBOOT = getAdbPath() + "reboot";

    //获取设备列表
    protected static final String DEVICES = getAdbPath() + "devices";
    //通过adb连接设备
    protected static final String CONNECT = getAdbPath() + "connect ";
    //判断设备状态
    protected static final String STATE = getAdbPath() + "get-state";
    //获取设备信息
    protected static final String PRODUCT_INFO = getAdbPath() + "shell cat /system/build.prop | grep product";
    //获取设备系统版本
    protected static final String PRODUCT_SYSTEM_VERSION = getAdbPath() + "shell getprop ro.build.version.release";
    //获取设备系统api版本
    protected static final String PRODUCT_SYSTEM_API_VERSION = getAdbPath() + "shell getprop ro.build.version.sdk";
    //获取设备型号
    protected static final String PRODUCT_MODEL = getAdbPath() + "shell getprop ro.product.model";
    //获取设备厂商
    protected static final String PRODUCT_BRAND = getAdbPath() + "shell getprop ro.product.brand";
    //获取设备序列号
    protected static final String SERIALNO = getAdbPath() + "shell getprop ro.serialno";
    //获取设备mac地址
    protected static final String MAC_ADDRESS = getAdbPath() + "shell cat /sys/class/net/wlan0/address";
    //获取IP地址
    protected static final String IP = getAdbPath() + "shell getprop dhcp.wlan0.ipaddress";
    //获取设备分辨率
    protected static final String RESOLUTION = getAdbPath() + "shell wm size";
    //获取设备内存信息
    protected static final String MEMINFO = getAdbPath() + "shell cat /proc/meminfo";
    //获取设备CPU最小频率
    protected static final String CPUINFO_MIN_FREQ = getAdbPath() + "shell cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
    //获取设备CPU最大频率
    protected static final String CPUINFO_MAX_FREQ = getAdbPath() + "shell cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
    //处理器型号
    protected static final String PRODUCT_BOARD = getAdbPath() + "shell cat /system/build.prop | grep ro.product.board";

    //列出目录下的文件和文件夹
    protected static final String LS_FOLDER_LIST = getAdbPath() + "shell ls /sys/devices/system/cpu";

    //安装应用
    protected static final String INSTALL_PACKAGE = getAdbPath() + "install -r -t ";
    //卸载应用 adb uninstall [-k]（-k 参数可选，表示卸载应用但保留数据和缓存目录）
    protected static final String UNINSTALL_PACKAGE = getAdbPath() + "uninstall ";
    //清除应用数据与缓存（相当于在设置里的应用信息界面点击了「清除缓存」和「清除数据」）
    protected static final String CLEARL_APP = getAdbPath() + "shell pm clear ";

    //启动指定的activity
    protected static final String START_ACTIVITY = getAdbPath() + "shell am start ";
    //启动指定的service
    protected static final String START_SERVICE = getAdbPath() + "shell am startservice ";
    //广播
    protected static final String BROADCAST = getAdbPath() + "shell am startservice ";
    //强制停止应用
    protected static final String FORCE_STOP = getAdbPath() + "shell am force-stop ";

    /**
     * 执行adb命令，并返回执行结果
     *
     * @param adbCommand adb命令
     * @param sn         设备标识（ip、序列号均可）
     * @return
     */
    protected static String execCommand(String adbCommand, String sn) {
        StringBuilder sb = new StringBuilder();
        try {
            process = Runtime.getRuntime().exec(String.format(adbCommand, splitSerialNumber(sn)));
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            while (StringUtils.isNotEmpty(Result = br.readLine())) {
                sb.append(Result + "\n");
            }
        } catch (IOException e) {
            logger.error("adb命令执行异常：", e);
        }
        return sb.toString();
    }

    /**
     * 指定目标设备（"-s ip:port" or "-s 序列号"）
     *
     * @param sn serialNumber
     * @return
     */
    protected static String splitSerialNumber(String sn) {
        if (sn.isEmpty()) {
            return "";
        } else if (CheckUtils.ipCheck(sn)) {
            return String.format("-s %s:5555 ", sn);
        } else {
            return String.format("-s %s ", sn);
        }
    }

}
