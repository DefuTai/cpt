package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.enums.ConnStatusEnum;
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
public class GetDevicesExecutor implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(GetDevicesExecutor.class);

    private DevicesDOMapper devicesDOMapper;

    private BlockingQueue<DevicesDO> queueDevices;

    public GetDevicesExecutor(DevicesDOMapper devicesDOMapper, BlockingQueue<DevicesDO> queueDevices) {
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
            //重新连接所有设备
            List<DevicesDO> devicesDOList = devicesDOMapper.selectAllDevices();
            //ADB获取设备列表
            Map<String, String> map = DeviceInfomation.getDevices();

            List<String> deviceIps = new ArrayList<>();
            List<String> offlineIps = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (ConnStatusEnum.DEVICE.getCode().equals(entry.getValue())) {
                    deviceIps.add(entry.getKey());
                }
                if (ConnStatusEnum.OFFLINE.getCode().equals(entry.getValue())) {
                    offlineIps.add(entry.getKey());
                }
            }

            List<Long> deviceIdList = new ArrayList<>();
            for (int i = 0; i < deviceIps.size(); i++) {
                for (DevicesDO devicesDO : devicesDOList) {
                    if (deviceIps.get(i).equals(devicesDO.getIp())) {
                        deviceIdList.add(devicesDO.getId());
                    }
                }
            }
            List<Long> offlineIdList = new ArrayList<>();
            for (int i = 0; i < offlineIps.size(); i++) {
                for (DevicesDO devicesDO : devicesDOList) {
                    if (offlineIps.get(i).equals(devicesDO.getIp())) {
                        queueDevices.offer(devicesDO);
                        offlineIdList.add(devicesDO.getId());
                    }
                }
            }

            if (deviceIdList.size() > 0) {
                devicesDOMapper.updateConnectStatus(ConstantDevice.CONNECT_STATUS_DEVICE, deviceIdList);
            }
            if (offlineIdList.size() > 0) {
                devicesDOMapper.updateConnectStatus(ConstantDevice.CONNECT_STATUS_OFFLINE, offlineIdList);
            }
        } catch (Exception e) {
            logger.error("检查离线设备异常：", e);
        }
    }

}
