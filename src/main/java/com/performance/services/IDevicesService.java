package com.performance.services;

import com.performance.pojo.DevicesDO;
import com.performance.query.DeviceQuery;
import com.performance.utils.PageBean;
import com.performance.utils.Result;

import java.util.List;

/**
 * @Author 鲢鱼
 * @Data 2019-08-14 23:41
 * @Version 1.0
 **/
public interface IDevicesService {

    /**
     * 获取设备信息
     *
     * @param devicesId
     * @return
     */
    Result queryDeviceInfo(long devicesId);

    /**
     * 获取设备列表
     *
     * @param query
     * @return
     */
    Result<PageBean<DevicesDO>> queryDeviceList(DeviceQuery query);

    /**
     * 获取所有设备信息
     *
     * @return
     */
    List<DevicesDO> queryAllDevices();

    /**
     * 存在则更新，不存在则新增
     *
     * @param devicesDO
     * @return
     */
    Result addOrModifyDevice(DevicesDO devicesDO);

    /**
     * 添加设备
     *
     * @param devicesDO
     * @return
     */
    Result addDevice(DevicesDO devicesDO);

    /**
     * 更新设备状态
     *
     * @param connectStatus
     * @param ids
     * @return
     */
    Result modifyConnectStatus(Integer connectStatus, List<Long> ids);

    /**
     * 修改设备信息
     *
     * @param devicesDO
     * @return
     */
    Result modifyDevice(DevicesDO devicesDO);

    /**
     * 选择更新
     *
     * @param devicesDO
     * @return
     */
    Result modifyDeviceelective(DevicesDO devicesDO);

    /**
     * 删除设备
     *
     * @param deviceIds
     * @return
     */
    Result removeDevice(List<Long> deviceIds);

    /**
     * 重启ADB服务
     *
     * @return
     */
    Result restartAdb();

}
