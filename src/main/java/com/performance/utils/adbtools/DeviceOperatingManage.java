package com.performance.utils.adbtools;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-16 15:29
 * @Version 1.0
 **/
public class DeviceOperatingManage extends Adb {

    /**
     * 安装应用
     *
     * @param sn          设备标识（ip、序列号均可）
     * @param packagePath 安装包地址
     * @return
     */
    public static String installPackage(String sn, String packagePath) {
        return execCommand(INSTALL_PACKAGE, sn, packagePath);
    }

    /**
     * 卸载应用
     *
     * @param sn          设备标识（ip、序列号均可）
     * @param packagePath 安装包地址
     * @return
     */
    public static String uninstallPackage(String sn, String packagePath) {
        return execCommand(UNINSTALL_PACKAGE, sn, packagePath);
    }

    /**
     * 清除应用数据与缓存
     *
     * @param sn          设备标识（ip、序列号均可）
     * @param packageName 应用包名
     * @return
     */
    public static String clearApp(String sn, String packageName) {
        return execCommand(CLEARL_APP, sn, packageName);
    }

    /**
     * 启动指定的activity
     *
     * @param sn       设备标识（ip、序列号均可）
     * @param activity
     * @return
     */
    public static String startActivity(String sn, String activity) {
        return execCommand(START_ACTIVITY, sn, activity);
    }

    /**
     * 启动指定的service
     *
     * @param sn      设备标识（ip、序列号均可）
     * @param service
     * @return
     */
    public static String startService(String sn, String service) {
        return execCommand(START_SERVICE, sn, service);
    }

    /**
     * 广播
     *
     * @param sn      设备标识（ip、序列号均可）
     * @param content
     * @return
     */
    public static String broadcast(String sn, String content) {
        return execCommand(BROADCAST, sn, content);
    }

    /**
     * 强制停止应用
     *
     * @param sn          设备标识（ip、序列号均可）
     * @param packageName
     * @return
     */
    public static String forceStop(String sn, String packageName) {
        return execCommand(FORCE_STOP, sn, packageName);
    }

    /**
     * 执行并返回结果
     *
     * @param adbCommand adb命令
     * @param sn         设备标识（IP和序列号均可）
     * @param content    待拼接内容
     * @return
     */
    protected static String execCommand(String adbCommand, String sn, String content) {
        StringBuilder sb = new StringBuilder();
        try {
            String command = String.format(adbCommand, splitSerialNumber(sn)) + content;
            logger.info("adb命令：" + command);
            process = Runtime.getRuntime().exec(command);
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            while (StringUtils.isNotEmpty(Result = br.readLine())) {
                sb.append(Result + "\n");
            }
        } catch (IOException e) {
            logger.error("adb命令执行异常：", e);
        }
        return sb.toString();
    }

}
