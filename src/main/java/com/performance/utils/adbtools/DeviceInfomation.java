package com.performance.utils.adbtools;

import com.performance.enums.ConnStatusEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：获取设备信息
 *
 * @Author 鲢鱼
 * @Data 2019-08-16 14:44
 * @Version 1.0
 **/
public class DeviceInfomation extends Adb {

    /**
     * 获取设备列表
     *
     * @return
     */
    public static Map getDevices() {
        Map<String, String> deviceMap = new HashMap<>();
        try {
            process = Runtime.getRuntime().exec(String.format(DEVICES, ""));
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            while (StringUtils.isNotEmpty(Result = br.readLine())) {
                if (Result.contains("daemon")) {
                    continue;
                } else if (Result.contains("List of devices")) {
                    continue;
                } else {
                    String[] var = Result.split("\\t");
                    if (var.length > 0) {
                        deviceMap.put(var[0].split(":")[0], var[1]);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("adb命令执行异常：", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("没有正常连接的设备：", e);
        } catch (Exception e) {
            logger.error("获取设备列表异常：", e);
        }
        return deviceMap;
    }

    /**
     * 判断设备状态
     *
     * @return 1.device    0.offline   -1.no device
     */
    public static Integer getState(String sn) {
        String output = execCommand(STATE, sn);

        for (ConnStatusEnum status : ConnStatusEnum.values()) {
            if (status.getCode().equals(output)) {
                return status.getValue();
            }
        }
        return null;
    }

    /**
     * 获取设备信息
     *
     * @param sn
     * @return
     */
    public static String getProductInfo(String sn) {
        return execCommand(PRODUCT_INFO, sn);
    }

    /**
     * 获取设备系统版本
     *
     * @param sn
     * @return
     */
    public static String getProductSystemVersion(String sn) {
        return execCommand(PRODUCT_SYSTEM_VERSION, sn);
    }

    /**
     * 获取设备系统api版本
     *
     * @param sn
     * @return
     */
    public static String getProductSystemApiVersion(String sn) {
        return execCommand(PRODUCT_SYSTEM_API_VERSION, sn);
    }

    /**
     * 获取设备型号
     *
     * @param sn
     * @return
     */
    public static String getProductModel(String sn) {
        String model = execCommand(PRODUCT_MODEL, sn);
        return interceptFirstLine(model);
    }

    /**
     * 获取设备厂商
     *
     * @param sn
     * @return
     */
    public static String getProductBrand(String sn) {
        return execCommand(PRODUCT_BRAND, sn);
    }

    /**
     * 获取设备序列号
     *
     * @param sn
     * @return
     */
    public static String getSerialNo(String sn) {
        return execCommand(SERIALNO, sn);
    }

    /**
     * 获取设备mac地址
     *
     * @param sn
     * @return
     */
    public static String getMacAddress(String sn) {
        String macAddress = execCommand(MAC_ADDRESS, sn);
        return interceptFirstLine(macAddress);
    }

    /**
     * 获取IP地址
     *
     * @param sn
     * @return
     */
    public static String getIp(String sn) {
        String ip = execCommand(IP, sn);
        return interceptFirstLine(ip);
    }

    /**
     * 获取设备分辨率
     *
     * @param sn
     * @return
     */
    public static String getResolution(String sn) {
        return execCommand(RESOLUTION, sn);
    }

    /**
     * 获取设备内存信息
     *
     * @param sn
     * @return
     */
    public static String getMeminfo(String sn) {
        return execCommand(MEMINFO, sn);
    }

    /**
     * 获取CPU核数
     *
     * @param sn
     * @return
     */
    public static int getCpuCore(String sn) {
        int core = 0;
        try {
            process = Runtime.getRuntime().exec(String.format(LS_FOLDER_LIST, splitSerialNumber(sn)));
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            String regex = "^cpu\\d{1,2}$";
            while (StringUtils.isNotEmpty(Result = br.readLine())) {
                if (Result.matches(regex)) {
                    core++;
                }
            }
        } catch (IOException e) {
            logger.error("adb命令执行异常：", e);
        }
        return core;
    }

    /**
     * 获取设备CPU最小频率
     *
     * @param sn
     * @return
     */
    public static String getCpuinfoMinFreq(String sn) {
        return execCommand(CPUINFO_MIN_FREQ, sn);
    }

    /**
     * 获取设备CPU最大频率
     *
     * @param sn
     * @return
     */
    public static String getCpuinfoMaxFreq(String sn) {
        return execCommand(CPUINFO_MAX_FREQ, sn);
    }

}
