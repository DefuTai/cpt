package com.performance.dao;

import com.performance.pojo.PerformanceDO;

public interface PerformanceDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PerformanceDO record);

    int insertSelective(PerformanceDO record);

    PerformanceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PerformanceDO record);

    int updateByPrimaryKey(PerformanceDO record);
}