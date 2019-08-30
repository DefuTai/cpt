package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.pojo.DevicesDO;
import com.performance.utils.ConstantDevice;
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
            logger.info("GetDeviceExecutor.run() ---> " + queueDevices.size());
            //获取所有已添加设备列表
            List<DevicesDO> devicesDOList = devicesDOMapper.selectAllDevices();
            //ADB获取设备列表
            Map<String, String> map = DeviceInfomation.getDevices();

            List<String> ips = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().contains("device")) {
                    ips.add(entry.getKey());
                }
            }
            //正常连接设备列表
            List<DevicesDO> devicesList = new ArrayList<>();
            for (int i = 0; i < ips.size(); i++) {
                for (DevicesDO devices : devicesDOList) {
                    if (ips.get(i).equals(devices.getIp())) {
                        devicesList.add(devices);
                    }
                }
            }
            for (DevicesDO devices : devicesList) {
                if (devices.getConnectStatus() == 0) {
                    List<Long> deviceIds = new ArrayList<>();
                    deviceIds.add(devices.getId());
                    devicesDOMapper.updateConnectStatus(ConstantDevice.CONNECT_STATUS_DEVICE, deviceIds);
                }
            }
            //把当前连接状态为device的记录从设备列表中移除
            devicesDOList.removeAll(devicesList);

            //离线设备ID
            List<Long> offlineDevicesIdList = new ArrayList<>();
            for (DevicesDO devices : devicesDOList) {
                //把当前连接状态为offline的设备放入待连接队列
                queueDevices.offer(devices);
                offlineDevicesIdList.add(devices.getId());
            }

            if (offlineDevicesIdList.size() > 0) {
                //更新设备记录adb连接状态为 0:offline
                devicesDOMapper.updateConnectStatus(ConstantDevice.CONNECT_STATUS_OFFLINE, offlineDevicesIdList);
            }
        } catch (Exception e) {
            logger.error("检查离线设备异常：", e);
        }
    }

}
