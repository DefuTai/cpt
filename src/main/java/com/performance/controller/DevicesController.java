package com.performance.controller;

import com.performance.enums.ResultEnum;
import com.performance.pojo.DevicesDO;
import com.performance.services.IDevicesService;
import com.performance.utils.BaseCPT;
import com.performance.utils.CheckUtils;
import com.performance.utils.ConstAdb;
import com.performance.utils.Result;
import com.performance.utils.adbtools.DeviceInfomation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IDevicesService devicesService;

    @PostMapping("/add")
    public Result addDevices(@Param("deviceName") String deviceName, @Param("ip") String ip) {
        if (StringUtils.isEmpty(deviceName) || StringUtils.isEmpty(ip)) {
            return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "设备名称或IP" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
        }
        if (!CheckUtils.ipCheck(ip)) {
            return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "IP地址不合法");
        }

        //通过adb connect ip连接设备
        String adbConn = ConstAdb.getConnect(ip);
        if (!adbConn.isEmpty() && adbConn.contains("connected to " + ip + ":5555")) {
            return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "ADB连接失败，请检查IP地址是否正确");
        }

        DevicesDO devices = new DevicesDO();
        devices.setDeviceName(deviceName);
        devices.setSystemType(1);
        devices.setIp(ip);
        devices.setConnectStatus(DeviceInfomation.getSTATE(ip));
        devices.setSystemVersion(DeviceInfomation.getProductSystemVersion(ip));
        devices.setMacAddress(DeviceInfomation.getMacAddress(ip));
        devices.setBrand(DeviceInfomation.getProductBrand(ip));
        devices.setResolution(DeviceInfomation.getResolution(ip).split(": ")[1].split("\n")[0]);
        devices.setRam(DeviceInfomation.getMeminfo(ip).split("\n")[0].split("        ")[1].split(" ")[0]);
        devices.setCore(String.valueOf(DeviceInfomation.getCpuCore(ip)));
        //TODO 信息待完善
        devices.setProcessor("");
        devices.setNetwork("");

        return devicesService.addDevices(devices);
    }

    public static void main(String[] args) {
        Process process;
        BufferedReader br;
        String content;

        try {
            process = Runtime.getRuntime().exec(ConstAdb.getResolution(""));
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            while ((content = br.readLine()) != null) {
                logger.info("xxx" + content);
                logger.info(content.split("x")[1]);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
