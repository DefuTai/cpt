package com.performance.dao;

import com.alibaba.fastjson.JSON;
import com.performance.pojo.DevicesDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevicesDOMapperTest {

    static Logger logger = LoggerFactory.getLogger(DevicesDOMapperTest.class);

    @Autowired
    DevicesDOMapper devicesDOMapper;

    @Test
    public void testSelectDeviceList() {
        DevicesDO devicesDO = new DevicesDO();
        devicesDO.setDeviceName("鲢鱼测试收银机");

        List<DevicesDO> list = devicesDOMapper.selectDeviceList(devicesDO);
        logger.info("DAO层返回结果：" + JSON.toJSONString(list));
    }

    @Test
    public void testSelectDevices() {
        List<DevicesDO> list = devicesDOMapper.selectDevices();
        logger.info("DAO层返回结果：" + JSON.toJSONString(list));
    }

    @Test
    public void testSelectDeviceByIp() {
        List<String> ips = new ArrayList<>();
        ips.add("10.1.130.59");
        List<DevicesDO> list = devicesDOMapper.selectDeviceByIps(ips);
        logger.info("DAO层返回结果：" + JSON.toJSONString(list));
    }

    @Test
    public void testSelectDeviceByIds() {
        List<Long> ips = new ArrayList<>();
        ips.add(123L);
        List<DevicesDO> list = devicesDOMapper.selectDeviceByIds(ips);
        logger.info("DAO层返回结果：" + JSON.toJSONString(list));
    }

    @Test
    public void testUpdateConnectStatus() {
        List<Long> ids = new ArrayList<>();
        ids.add(55558861558L);
        int row = devicesDOMapper.updateConnectStatus(0, ids);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        List<Long> ids = new ArrayList<>();
        ids.add(55551796318L);
        ids.add(55555610126L);
        int row = devicesDOMapper.deleteByPrimaryKey(ids);
    }

}