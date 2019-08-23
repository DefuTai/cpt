package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.pojo.DevicesDO;

import java.util.concurrent.*;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-23 16:29
 * @Version 1.0
 **/
public class ConnectService {

    private DevicesDOMapper devicesDOMapper;

    private BlockingQueue<DevicesDO> devicesDOBlockingQueue;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
    private final ConnectDeviceExecutor connectDeviceExecutor;
    private final GetDeviceExecutor getDeviceExecutor;

    public ConnectService(DevicesDOMapper devicesDOMapper) {
        this.devicesDOMapper = devicesDOMapper;
        this.devicesDOBlockingQueue = new LinkedBlockingQueue<>(16);
        this.connectDeviceExecutor = new ConnectDeviceExecutor(devicesDOMapper, devicesDOBlockingQueue);
        this.getDeviceExecutor = new GetDeviceExecutor(devicesDOMapper, devicesDOBlockingQueue);
    }

    public void start() {
        executorService.scheduleWithFixedDelay(getDeviceExecutor, 0, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(connectDeviceExecutor, 0, 10, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdown();
    }

    public BlockingQueue<DevicesDO> getDeviceModels() {
        return devicesDOBlockingQueue;
    }

}
