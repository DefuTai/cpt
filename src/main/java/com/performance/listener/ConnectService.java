package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.po.DevicesDO;
import com.performance.services.IDevicesService;
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
    @Autowired
    private IDevicesService devicesService;

    private BlockingQueue<DevicesDO> devicesDOBlockingQueue = new LinkedBlockingQueue<>(20);
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    public void $_start() {
        GetDevicesExecutor getDevicesExecutor = new GetDevicesExecutor(this.devicesDOMapper, devicesDOBlockingQueue);
        ConnectDevicesExecutor connectDevicesExecutor = new ConnectDevicesExecutor(this.devicesDOMapper, devicesDOBlockingQueue);

        executorService.scheduleWithFixedDelay(getDevicesExecutor, 0, 15, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(connectDevicesExecutor, 0, 15, TimeUnit.SECONDS);
    }

    /**
     * 轮询扫描更新设备连接状态
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void start() {
        GetDevicesExecutor getDevicesExecutor = new GetDevicesExecutor(this.devicesService);
        executorService.scheduleWithFixedDelay(getDevicesExecutor, 0, 60, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdown();
    }

    public BlockingQueue<DevicesDO> getDeviceModels() {
        return devicesDOBlockingQueue;
    }

}
