package com.performance.dao;

import com.performance.pojo.PerFrequencyDO;

public interface PerFrequencyDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PerFrequencyDO record);

    int insertSelective(PerFrequencyDO record);

    PerFrequencyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PerFrequencyDO record);

    int updateByPrimaryKey(PerFrequencyDO record);
}