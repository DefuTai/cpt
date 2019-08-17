package com.performance.utils.adbtools;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：设备连接管理
 *
 * @Author 鲢鱼
 * @Data 2019-08-16 14:33
 * @Version 1.0
 **/
public class DeviceConnectManage extends Adb {

    /**
     * 重启adb服务
     */
    public static void getStartServer() {
        try {
            process = Runtime.getRuntime().exec(String.format(REBOOT, splitSerialNumber(START_SERVER)));
        } catch (IOException e) {
            logger.error("重启adb服务异常：", e);
            e.printStackTrace();
        }
    }

    /**
     * 终止adb服务
     */
    public static void getKillServer() {
        try {
            process = Runtime.getRuntime().exec(String.format(REBOOT, splitSerialNumber(KILL_SERVER)));
        } catch (IOException e) {
            logger.error("终止adb服务异常：", e);
            e.printStackTrace();
        }
    }

    /**
     * 重启设备
     *
     * @param sn 设备标识（ip和序列号都行）
     */
    public static void getReboot(String sn) {
        try {
            process = Runtime.getRuntime().exec(String.format(REBOOT, splitSerialNumber(sn)));
        } catch (IOException e) {
            logger.error("重启设备异常：", e);
            e.printStackTrace();
        }
    }

    /**
     * adb连接远程设备
     *
     * @param ip
     * @return
     */
    public static final String getConnect(String ip) {
        StringBuilder sb = new StringBuilder();
        try {
            process = Runtime.getRuntime().exec(String.format(CONNECT, "") + ip + ":5555");
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            while (StringUtils.isNotEmpty(Result = br.readLine())) {
                sb.append(Result + "\n");
            }
        } catch (IOException e) {
            logger.error("adb connect命令执行异常：", e);
        }
        return sb.toString();
    }

}
