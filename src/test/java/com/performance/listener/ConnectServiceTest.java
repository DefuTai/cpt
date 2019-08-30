package com.performance.listener;

import com.alibaba.fastjson.JSON;
import com.performance.dao.DevicesDOMapper;
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
public class ConnectServiceTest {

    static Logger logger = LoggerFactory.getLogger(ConnectServiceTest.class);

    @Autowired
    DevicesDOMapper devicesDOMapper;

    @Test
    public void testStart() {
        List<String> list = new ArrayList<>();
        list.add("sdf");
        list.add("sdf");
        list.add("sdf");
        System.out.println(JSON.toJSONString(list));

        List<String> list2 = new ArrayList<>();
        list2.add("sdf");

        list.removeAll(list2);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testStop() {
    }

    @Test
    public void testGetDeviceModels() {
    }
}