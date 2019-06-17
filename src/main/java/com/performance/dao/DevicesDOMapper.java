package com.performance.dao;

import com.performance.pojo.DevicesDO;

public interface DevicesDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevicesDO record);

    int insertSelective(DevicesDO record);

    DevicesDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevicesDO record);

    int updateByPrimaryKey(DevicesDO record);
}