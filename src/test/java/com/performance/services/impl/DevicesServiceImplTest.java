package com.performance.services.impl;

import com.alibaba.fastjson.JSON;
import com.performance.pojo.DevicesDO;
import com.performance.query.DeviceQuery;
import com.performance.services.IDevicesService;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevicesServiceImplTest {

    static Logger logger = LoggerFactory.getLogger(DevicesServiceImplTest.class);

    @Autowired
    IDevicesService devicesService;

    @Test
    public void testQueryDeviceInfo() {
    }

    @Test
    public void testQueryDeviceList() {
        DeviceQuery query = new DeviceQuery();
        query.setDeviceName("鲢鱼测试收银机");
        query.setIndex(2);
        query.setPageSize(10);

        Result<PageBean<DevicesDO>> list1 = devicesService.queryDeviceList(query);
        logger.info("返回结果1：" + JSON.toJSONString(list1.getData()));
    }

    @Test
    public void testAddDevice() {
    }

    @Test
    public void testModifyDevice() {
    }

    @Test
    public void testModifyDeviceelective() {
    }

    @Test
    public void testRemoveDevice() {
    }

}