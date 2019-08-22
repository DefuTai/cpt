package com.performance.controller;

import com.performance.BaseCPT;
import com.performance.enums.ResultEnum;
import com.performance.pojo.DevicesDO;
import com.performance.query.DeviceQuery;
import com.performance.services.IDevicesService;
import com.performance.utils.CheckUtils;
import com.performance.utils.Result;
import com.performance.utils.adbtools.DeviceConnectManage;
import com.performance.utils.adbtools.DeviceInfomation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/list")
    public Result queryDeviceList(DeviceQuery query, @Param("index") Integer index, @Param("pageSize") Integer pageSize) {
        return devicesService.queryDeviceList(query, index, pageSize);
    }

    @PostMapping("/info")
    public Result queryDeviceInfo(@Param("id") long id) {
        return devicesService.queryDeviceInfo(id);
    }

    @PostMapping("/add")
    public Result addDevices(@Param("deviceName") String deviceName, @Param("ip") String ip) {
        if (StringUtils.isEmpty(deviceName) || StringUtils.isEmpty(ip)) {
            return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "设备名称或IP" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
        }
        if (!CheckUtils.ipCheck(ip)) {
            return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "IP地址不合法");
        }

        //通过adb connect ip连接设备
        String adbConn = DeviceConnectManage.getConnect(ip);
        if (adbConn.isEmpty() || !adbConn.contains("connected to " + ip + ":5555")) {
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
        devices.setCore(Integer.valueOf(String.valueOf(DeviceInfomation.getCpuCore(ip))));
        devices.setModel(DeviceInfomation.getProductModel(ip));

        return devicesService.addDevice(devices);
    }

    @PostMapping("/modify")
    public Result modifyDevice(DevicesDO devicesDO) {
        return devicesService.modifyDevice(devicesDO);
    }

    @PostMapping("/remove")
    public Result removeDevices(@Param("deviceIds") List<Long> deviceIds) {
        return devicesService.removeDevice(deviceIds);
    }

}
