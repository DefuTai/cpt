package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.pojo.DevicesDO;
import com.performance.utils.adbtools.DeviceInfomation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * 描述：获取待连接设备
 *
 * @Author 鲢鱼
 * @Data 2019-08-23 15:39
 * @Version 1.0
 **/
public class GetDeviceExecutor implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(GetDeviceExecutor.class);

    private DevicesDOMapper devicesDOMapper;

    private BlockingQueue<DevicesDO> queueDevices;

    public GetDeviceExecutor(DevicesDOMapper devicesDOMapper, BlockingQueue<DevicesDO> queueDevices) {
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
            //获取所有已添加设备列表
            List<DevicesDO> devicesDOList = devicesDOMapper.selectDevices();
            //ADB获取设备列表
            Map<String, String> map = DeviceInfomation.getDevices();

            List<String> ips = null;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().contains("device")) {
                    ips.add(entry.getKey());
                }
            }
            //正常连接设备列表
            List<DevicesDO> devicesList = devicesDOMapper.selectDeviceByIps(ips);
            devicesDOList.removeAll(devicesList);

            //离线设备ID
            List<Long> offlineDevicesIdList = new ArrayList<>();
            for (DevicesDO devices : devicesDOList) {
                //把当前未正常连接的设备放入队列
                queueDevices.offer(devices);
                offlineDevicesIdList.add(devices.getId());
            }
            //更新设备记录adb连接状态为 0:offline
            devicesDOMapper.updateConnectStatus(0, offlineDevicesIdList);
        } catch (Exception e) {
            logger.error("检查离线设备异常：", e);
        }
    }

}
