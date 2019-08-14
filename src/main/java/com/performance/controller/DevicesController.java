package com.performance.controller;

import com.performance.enums.ResultEnum;
import com.performance.pojo.DevicesDO;
import com.performance.utils.BaseCPT;
import com.performance.utils.ConstAdb;
import com.performance.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-14 10:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cpt/devices")
public class DevicesController extends BaseCPT {

    private static final Logger logger = LoggerFactory.getLogger(DevicesController.class);

    @PostMapping("/add")
    public Result addDevices(@Param("deviceName") String deviceName, @Param("ip") String ip) {
        if (StringUtils.isEmpty(deviceName) || StringUtils.isEmpty(ip)) {
            return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "设备名称或IP" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
        }
        if (!ipCheck(ip)) {
            return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "IP地址不合法");
        }

        DevicesDO devices = new DevicesDO();
        devices.setDeviceName(deviceName);

        Process process;
        BufferedReader br;
        String Result;
        try {
            process = Runtime.getRuntime().exec(ConstAdb.getConnect(ip));
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            while ((Result = br.readLine()) != null) {
                if (Result.contains("connected to")) {
                    logger.info("ADB CONNECT连接成功！");
                }
            }
            process = Runtime.getRuntime().exec(ConstAdb.getDevices());
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            while ((Result = br.readLine()) != null) {
                if (Result.contains(ip) && Result.contains("device")) {
                    logger.info("设备[" + ip + "]连接成功！");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean ipCheck(String str) {
        if (str != null && !str.isEmpty()) {
            // 定义正则表达式
            String regex = "^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$";
            if (str.matches(regex)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(ipCheck("10.1.130.59"));
        System.out.println(ipCheck("192.68.0.1"));
        System.out.println(ipCheck("255.255.255.255"));
        System.out.println(ipCheck("256.255.255.255"));
        System.out.println(ipCheck("255.256.255.255"));
        System.out.println(ipCheck("255.255.256.255"));
        System.out.println(ipCheck("255.255.255.256"));
        System.out.println(ipCheck("0.0.0.0"));
        System.out.println(ipCheck("0.68.0.1"));
        System.out.println(ipCheck("test"));
    }

}
