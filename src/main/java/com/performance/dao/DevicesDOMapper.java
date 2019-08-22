package com.performance.dao;

import com.performance.pojo.DevicesDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DevicesDOMapper {

    /**
     * 获取设备信息
     *
     * @param id
     * @return
     */
    DevicesDO selectByPrimaryKey(Long id);

    /**
     * 获取设备列表
     *
     * @param devicesDO
     * @return
     */
    List<DevicesDO> selectDeviceList(DevicesDO devicesDO);

//    List<DevicesDO> selectDeviceList2(DevicesDO devicesDO);

    /**
     * 获取设备列表总记录数
     *
     * @param devicesDO
     * @return
     */
    int selectDeviceListCount(DevicesDO devicesDO);

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
     * 删除设备记录
     *
     * @param ids
     * @return
     */
    int deleteByPrimaryKey(List<Long> ids);

}