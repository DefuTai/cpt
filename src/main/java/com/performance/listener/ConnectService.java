package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.pojo.DevicesDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-23 16:29
 * @Version 1.0
 **/
@Service
public class ConnectService {

    @Autowired
    private DevicesDOMapper devicesDOMapper;

    private BlockingQueue<DevicesDO> devicesDOBlockingQueue = new LinkedBlockingQueue<>(20);
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    @Scheduled(fixedRate = 30 * 1000)
    public void start() {
        GetDeviceExecutor getDeviceExecutor = new GetDeviceExecutor(this.devicesDOMapper, devicesDOBlockingQueue);
        ConnectDeviceExecutor connectDeviceExecutor = new ConnectDeviceExecutor(this.devicesDOMapper, devicesDOBlockingQueue);

        executorService.scheduleWithFixedDelay(getDeviceExecutor, 0, 10, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(connectDeviceExecutor, 0, 10, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdown();
    }

    public BlockingQueue<DevicesDO> getDeviceModels() {
        return devicesDOBlockingQueue;
    }

}
