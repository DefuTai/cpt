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
}