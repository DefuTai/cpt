package com.performance.dao;

import com.performance.pojo.DevicesDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DevicesDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevicesDO record);

    int insertSelective(DevicesDO record);

    DevicesDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevicesDO record);

    int updateByPrimaryKey(DevicesDO record);
}