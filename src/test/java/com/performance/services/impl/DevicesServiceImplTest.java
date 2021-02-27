package com.performance.services.impl;

import com.alibaba.fastjson.JSON;
import com.performance.po.DevicesDO;
import com.performance.query.DeviceQuery;
import com.performance.services.IDevicesService;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DevicesServiceImplTest {

    @Autowired
    IDevicesService devicesService;

    @Test
    public void testQueryDeviceList() {
        DeviceQuery query = new DeviceQuery();
        query.setSystemType(1);
        query.setUseStatus(1);

        Result<PageBean<DevicesDO>> result = devicesService.queryDeviceList(query);

        log.info("DevicesServiceImplTest#testQueryDeviceList test result is {}", JSON.toJSONString(result));
        Assert.assertEquals("返回状态码有误！", result.getCode(), "2000");
    }

    @Test
    public void testAddDevice() {
//        String deviceName = "DF" + Thread.currentThread().getId() + System.currentTimeMillis();
//
//        DevicesDO devicesDO = new DevicesDO();
//        devicesDO.setDeviceName(deviceName);
//        devicesDO.setSystemType(1);
//        devicesDO.setSystemVersion("1.1.1");
//        devicesDO.setCore(8);
//        devicesDO.setRam("RAM");
//        devicesDO.setNetwork(1);
//        devicesDO.setIp("127.0.0.1");
//        devicesDO.setSerialNumber("SERIALNUMBER");
//        devicesDO.setMacAddress("" + Thread.currentThread().getId() + System.currentTimeMillis());
//        devicesDO.setBrand("BRAND");
//        devicesDO.setModel("MODEL");
//        devicesDO.setResolution("1920*1080");
//        devicesDO.setConnectStatus(0);
//
//        Result result = devicesService.addDevice(devicesDO);
//        Assert.assertEquals("返回结果校验失败！", result.getCode(), "2000");
//
//        DeviceQuery query = new DeviceQuery();
//        query.setDeviceName(deviceName);
//
//        Result<PageBean<DevicesDO>> queryDeviceList = devicesService.queryDeviceList(query);
//        log.info("DevicesServiceImplTest#testAddDevice queryDeviceList is {}", JSON.toJSONString(queryDeviceList));
//        Assert.assertTrue("查询结果有误！", queryDeviceList.getData().getPageData().size() == 1);
//        List<DevicesDO> devicesDOList = queryDeviceList.getData().getPageData();
//        Assert.assertEquals("设备系统类型校验失败！", devicesDOList.get(0).getSystemType(), devicesDO.getSystemType());
//        Assert.assertEquals("设备系统版本校验失败！", devicesDOList.get(0).getSystemVersion(), devicesDO.getSystemVersion());
//        Assert.assertEquals("设备核心数校验失败！", devicesDOList.get(0).getCore(), devicesDO.getCore());
//        Assert.assertEquals("设备内存校验失败！", devicesDOList.get(0).getRam(), devicesDO.getRam());
//        Assert.assertEquals("设备网络类型校验失败！", devicesDOList.get(0).getNetwork(), devicesDO.getNetwork());
//        Assert.assertEquals("设备IP校验失败！", devicesDOList.get(0).getIp(), devicesDO.getIp());
//        Assert.assertEquals("设备序列号校验失败！", devicesDOList.get(0).getSerialNumber(), devicesDO.getSerialNumber());
//        Assert.assertEquals("设备MAC地址校验失败！", devicesDOList.get(0).getMacAddress(), devicesDO.getMacAddress());
//        Assert.assertEquals("设备品牌校验失败！", devicesDOList.get(0).getBrand(), devicesDO.getBrand());
//        Assert.assertEquals("设备型号校验失败！", devicesDOList.get(0).getModel(), devicesDO.getModel());
//        Assert.assertEquals("设备分辨率校验失败！", devicesDOList.get(0).getResolution(), devicesDO.getResolution());
//        Assert.assertEquals("设备连接状态校验失败！", devicesDOList.get(0).getConnectStatus(), devicesDO.getConnectStatus());
//        Assert.assertTrue("设备使用状态校验失败！", devicesDOList.get(0).getUseStatus() == 0);
    }

    @Test
    public void testModifyDevice() {
        DevicesDO devicesDO = new DevicesDO();
        devicesDO.setId(555534131699389994L);
        devicesDO.setConnectStatus(1);
        Result result = devicesService.modifyDevice(devicesDO);

        log.info("DevicesServiceImplTest#testModifyDevice result is {}", JSON.toJSONString(result));
        Assert.assertEquals("返回状态码有误！", result.getCode(), "2000");

//        Result<DevicesDO> deviceById = devicesService.findDeviceById(devicesDO.getId());
//        log.info("DevicesServiceImplTest#testModifyDevice deviceById is {}", JSON.toJSONString(deviceById));
//        Assert.assertEquals("修改结果校验失败！", deviceById.getData().getConnectStatus(), devicesDO.getConnectStatus());
    }

    @Test
    public void testModifyDeviceElective() {
        DevicesDO devicesDO = new DevicesDO();
        devicesDO.setId(555534131699389994L);
        devicesDO.setConnectStatus(0);

        Result result = devicesService.modifyDeviceElective(devicesDO);
        log.info("DevicesServiceImplTest#testModifyDeviceElective result is {}", JSON.toJSONString(result));
        Assert.assertEquals("返回状态码有误！", result.getCode(), "2000");

//        Result<DevicesDO> deviceById = devicesService.findDeviceById(devicesDO.getId());
//        log.info("DevicesServiceImplTest#testModifyDevice deviceById is {}", JSON.toJSONString(deviceById));
//        Assert.assertEquals("修改结果校验失败！", deviceById.getData().getConnectStatus(), devicesDO.getConnectStatus());
    }

    @Test
    public void testRemoveDevice() {
        Long deviceId = 555534131699389994L;

        List<Long> deviceIds = new ArrayList<>();
        deviceIds.add(deviceId);
        Result result = devicesService.removeDevice(deviceIds);
        Assert.assertEquals("返回状态码有误！", result.getCode(), "4444");

//        Result<DevicesDO> resultDevice = devicesService.findDeviceById(deviceId);
//        Assert.assertNull("删除设备失败！", resultDevice.getData());
    }

}