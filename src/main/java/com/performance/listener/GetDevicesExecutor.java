package com.performance.listener;

import com.performance.dao.DevicesDOMapper;
import com.performance.enums.ConnStatusEnum;
import com.performance.pojo.DevicesDO;
import com.performance.services.IDevicesService;
import com.performance.utils.ConstantDevice;
import com.performance.utils.adbtools.DeviceInfomation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
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
    private IDevicesService devicesService;

    //把待连接设备存放到队列（需求变更：20190908）
    private BlockingQueue<DevicesDO> queueDevices;

    public GetDevicesExecutor(IDevicesService devicesService) {
        if (devicesService == null) {
            throw new IllegalArgumentException("DevicesDOMapper cannot be null...");
        }
        this.devicesService = devicesService;
    }

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
            devicesScanning();
        } catch (Exception e) {
            logger.error("检查离线设备异常：", e);
        }
    }

    /**
     * 更新设备状态
     *
     * @param devicesDOList
     * @param devicesDOMapper
     */
    public static void devicesStatusUpdate(List<DevicesDO> devicesDOList, DevicesDOMapper devicesDOMapper) {
        Map<String, String> map = DeviceInfomation.getDevices();

        List<String> deviceSNList = new ArrayList<>();
        List<String> offlineSNList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (ConnStatusEnum.DEVICE.getCode().equals(entry.getValue())) {
                deviceSNList.add(entry.getKey());
            }
            if (ConnStatusEnum.OFFLINE.getCode().equals(entry.getValue())) {
                offlineSNList.add(entry.getKey());
            }
        }

        List<Long> deviceIdList = findIdBySN(devicesDOList, deviceSNList);
        if (deviceIdList.size() > 0) {
            devicesDOMapper.updateConnectStatus(ConstantDevice.CONNECT_STATUS_DEVICE, deviceIdList);
        }

        List<Long> offlineIdList = findIdBySN(devicesDOList, offlineSNList);
        if (offlineIdList.size() > 0) {
            devicesDOMapper.updateConnectStatus(ConstantDevice.CONNECT_STATUS_OFFLINE, offlineIdList);
        }
    }

    private static List<Long> findIdBySN(List<DevicesDO> devicesDOList, List<String> snList) {
        List<Long> deviceIdList = new ArrayList<>();
        for (int i = 0; i < snList.size(); i++) {
            for (DevicesDO devicesDO : devicesDOList) {
                if (snList.get(i).equals(devicesDO.getSerialNumber())) {
                    deviceIdList.add(devicesDO.getId());
                }
            }
        }
        return deviceIdList;
    }

    /**
     * 扫描设备状态
     */
    private void devicesScanning() {
        //获取DB内所有已记录设备
        List<DevicesDO> devicesList = devicesService.queryAllDevices();
        //当前已连接设备
        Map<String, String> devicesMap = DeviceInfomation.getDevices();

        if (devicesList.isEmpty() && devicesMap.isEmpty()) {
            return;
        }

        //遍历adb devices下所有记录，有则更新，无则新增
        for (Map.Entry<String, String> deviceEntry : devicesMap.entrySet()) {
            DevicesDO device = new DevicesDO();

            device.setSystemType(1);
            device.setSystemVersion(DeviceInfomation.getProductSystemVersion(deviceEntry.getKey()));
            device.setCore(Integer.valueOf(String.valueOf(DeviceInfomation.getCpuCore(deviceEntry.getKey()))));
            device.setRam(DeviceInfomation.getMeminfo(deviceEntry.getKey()).split("\n")[0].split("        ")[1].split(" ")[0]);
            device.setNetwork(null);
            device.setIp(DeviceInfomation.getIp(deviceEntry.getKey()));
            device.setSerialNumber(deviceEntry.getKey());
            device.setMacAddress(DeviceInfomation.getMacAddress(deviceEntry.getKey()));
            device.setBrand(DeviceInfomation.getProductBrand(deviceEntry.getKey()));
            device.setModel(DeviceInfomation.getProductModel(deviceEntry.getKey()));
            device.setResolution(DeviceInfomation.getResolution(deviceEntry.getKey()).split(": ")[1].split("\n")[0]);
            device.setConnectStatus(ConnStatusEnum.getValueByCode(deviceEntry.getValue()));

            devicesService.addOrModifyDevice(device);
        }

        //移除adb devices中已存在的设备记录
        for (Iterator<DevicesDO> it = devicesList.iterator(); it.hasNext(); ) {
            for (Map.Entry<String, String> deviceEntry : devicesMap.entrySet()) {
                if (it.next().equals(deviceEntry.getKey())) {
                    it.remove();
                }
            }
        }

        //更新剩下所有数据库记录为no device
        if (!devicesList.isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (DevicesDO devicesDO : devicesList) {
                ids.add(devicesDO.getId());
            }
            devicesService.modifyConnectStatus(ConstantDevice.CONNECT_STATUS_NO_DEVICE, ids);
        }
    }

}
