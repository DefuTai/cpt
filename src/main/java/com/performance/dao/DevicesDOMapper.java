package com.performance.dao;

import com.performance.po.DevicesDO;
import com.performance.query.DeviceQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DevicesDOMapper {

    /**
     * 根据ID获取设备信息
     *
     * @param id
     * @return
     */
    DevicesDO selectByPrimaryKey(Long id);

    /**
     * 根据多个ID，获取多个设备信息
     *
     * @param ids
     * @return
     */
    List<DevicesDO> selectDeviceByIds(@Param("ids") List<Long> ids);

    /**
     * 根据多个IP，获取多个设备信息
     *
     * @param ips
     * @return
     */
    List<DevicesDO> selectDeviceByIps(@Param("ips") List<String> ips);

    /**
     * 根据设备名称和IP地址查询是否已存在（去重）
     *
     * @param deviceName
     * @param ip
     * @return
     */
    List<DevicesDO> deduplication(String deviceName, String ip);

    /**
     * 获取设备列表
     *
     * @param query
     * @return
     */
    List<DevicesDO> selectDeviceList(DeviceQuery query);

    /**
     * 获取设备列表总记录数
     *
     * @param query
     * @return
     */
    int selectDeviceListCount(DeviceQuery query);

    /**
     * 获取所有设备信息
     *
     * @return
     */
    List<DevicesDO> selectAllDevices();

    /**
     * 存在则更新，不存在则新增
     *
     * @param devicesDO
     * @return
     */
    int insertOrUpdate(DevicesDO devicesDO);

    /**
     * 添加设备
     *
     * @param record
     * @return
     */
    int insert(DevicesDO record);

    /**
     * 修改设备信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(DevicesDO record);

    /**
     * 选择更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DevicesDO record);

    /**
     * 更新设备状态
     *
     * @param connectStatus
     * @param ids
     * @return
     */
    int updateConnectStatus(@Param("connectStatus") Integer connectStatus, @Param("ids") List<Long> ids);

    /**
     * 删除设备记录
     *
     * @param ids
     * @return
     */
    int deleteByPrimaryKey(List<Long> ids);

}