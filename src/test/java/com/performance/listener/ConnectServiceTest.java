package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectServiceTest {

    static Logger logger = LoggerFactory.getLogger(ConnectServiceTest.class);

    @Autowired
    DevicesDOMapper devicesDOMapper;

    @Test
    public void testStart() {
        ConnectService connectService = new ConnectService(devicesDOMapper);
        connectService.start();
    }

    @Test
    public void testStop() {
    }

    @Test
    public void testGetDeviceModels() {
    }
}