package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.pojo.DevicesDO;
import com.performance.utils.adbtools.DeviceConnectManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * 描述：连接设备
 *
 * @Author 鲢鱼
 * @Data 2019-08-23 15:53
 * @Version 1.0
 **/
public class ConnectDeviceExecutor implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ConnectDeviceExecutor.class);

    private DevicesDOMapper devicesDOMapper;

    private BlockingQueue<DevicesDO> queueDevices;

    public ConnectDeviceExecutor(DevicesDOMapper devicesDOMapper, BlockingQueue<DevicesDO> queueDevices) {
        if (devicesDOMapper == null) {
            throw new IllegalArgumentException("DevicesDOMapper cannot be null...");
        }
        if (queueDevices == null) {
            throw new IllegalArgumentException("BlockingQueue<DevicesDO> cannot be null...");
        }

        this.devicesDOMapper = devicesDOMapper;
        this.queueDevices = queueDevices;
    }

    @Override
    public void run() {
        try {
            List<Long> ids = null;
            for (DevicesDO devicesDO : queueDevices) {
                logger.info("adb connect " + devicesDO.getIp() + ":5555 ...");
                String adbConn = DeviceConnectManage.getConnect(devicesDO.getIp());
                if (adbConn.contains("connected to " + devicesDO.getIp() + ":5555")) {
                    try {
                        //从队列中删除
                        queueDevices.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ids.add(devicesDO.getId());
                } else {
                    logger.warn(adbConn);
                }
            }
            //更新设备记录adb连接状态为 1:devices
            devicesDOMapper.updateConnectStatus(1, ids);
        } catch (Exception e) {
            logger.error("自动连接设备异常：", e);
        }
    }

}
