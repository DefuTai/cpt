package com.performance.dao;

import com.performance.pojo.ExecutionTaskDO;

public interface ExecutionTaskDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExecutionTaskDO record);

    int insertSelective(ExecutionTaskDO record);

    ExecutionTaskDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExecutionTaskDO record);

    int updateByPrimaryKey(ExecutionTaskDO record);
}