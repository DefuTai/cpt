package com.performance.services.impl;

import com.performance.BaseCPT;
import com.performance.dao.DevicesDOMapper;
import com.performance.enums.ConnStatusEnum;
import com.performance.enums.ResultEnum;
import com.performance.pojo.DevicesDO;
import com.performance.query.DeviceQuery;
import com.performance.services.IDevicesService;
import com.performance.utils.ConstantDevice;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import com.performance.utils.UuidUtil;
import com.performance.utils.adbtools.DeviceConnectManage;
import com.performance.utils.adbtools.DeviceInfomation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-14 23:42
 * @Version 1.0
 **/
@Service
public class DevicesServiceImpl extends BaseCPT implements IDevicesService {

    private static Logger logger = LoggerFactory.getLogger(DevicesServiceImpl.class);

    @Autowired
    private DevicesDOMapper devicesDOMapper;

    @Override
    public Result<DevicesDO> queryDeviceInfo(long devicesId) {
        DevicesDO devicesDO;
        try {
            devicesDO = devicesDOMapper.selectByPrimaryKey(devicesId);
        } catch (Exception e) {
            logger.error("获取设备信息异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return resultUtil.success(devicesDO == null ? new DevicesDO() : devicesDO);
    }

    @Override
    public Result<PageBean<DevicesDO>> queryDeviceList(DeviceQuery query, Integer index, Integer pageSize) {
        PageBean<DevicesDO> devicesPageBean;
        try {
            DevicesDO devicesDO = new DevicesDO();
            BeanUtils.copyProperties(query, devicesDO);

            PageBean pageBean = new PageBean();
            devicesDO.setPageSize((pageSize != null) ? pageSize : pageBean.getPageSize());
            devicesDO.setIndex((pageSize != null && index != null) ? pageSize * (index - 1) : pageBean.getPageSize() * (pageBean.getCurrentPage() - 1));

            List<DevicesDO> list = devicesDOMapper.selectDeviceList(devicesDO);

            int count = devicesDOMapper.selectDeviceListCount(devicesDO);

            devicesPageBean = new PageBean<>(list, count);
        } catch (Exception e) {
            logger.error("获取设备列表异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return resultUtil.success(devicesPageBean);
    }

    @Override
    public Result addDevice(DevicesDO devicesDO) {
        try {
            List<DevicesDO> devices = devicesDOMapper.deduplication(devicesDO.getDeviceName(), devicesDO.getIp());
            if (!devices.isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "名称或IP相同的记录已存在，请确认后重试！");
            }

            devicesDO.setId(Long.valueOf(UuidUtil.getUuid()));
            devicesDO.setUseStatus(0);

            //TODO 可以考虑添加序列号校验逻辑，相同序列号设备视为同一设备，添加时直接更新原有记录

            int num = devicesDOMapper.insert(devicesDO);

            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("添加设备信息异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result modifyDevice(DevicesDO devicesDO) {
        try {
            int num = devicesDOMapper.updateByPrimaryKey(devicesDO);
            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("修改设备信息异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result modifyDeviceelective(DevicesDO devicesDO) {
        try {
            int num = devicesDOMapper.updateByPrimaryKeySelective(devicesDO);
            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("修改设备信息异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result removeDevice(List<Long> deviceIds) {
        try {
            List<DevicesDO> deviceList = devicesDOMapper.selectDeviceByIds(deviceIds);
            List<Long> newDeviceIds = new ArrayList<>();
            if (deviceList.isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "设备不存在或已删除，请刷新后重试！");
            } else {
                for (DevicesDO devices : deviceList) {
                    newDeviceIds.add(devices.getId());
                }
            }

            int num = devicesDOMapper.deleteByPrimaryKey(newDeviceIds);
            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("删除设备异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result restartAdb() {
        try {
            //重启adb服务
            DeviceConnectManage.getKillServer();
            DeviceConnectManage.getStartServer();

            //重新连接所有设备
            List<DevicesDO> devicesDOList = devicesDOMapper.selectAllDevices();
            for (DevicesDO devicesDO : devicesDOList) {
                DeviceConnectManage.getConnect(devicesDO.getIp());
            }

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
            logger.error("重启ADB服务异常：", e);
            return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "重启ADB服务异常!");
        }
        return resultUtil.success();
    }

}
