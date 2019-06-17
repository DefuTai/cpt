package com.performance.dao;

import com.performance.pojo.ExecutionPlanDO;

public interface ExecutionPlanDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExecutionPlanDO record);

    int insertSelective(ExecutionPlanDO record);

    ExecutionPlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExecutionPlanDO record);

    int updateByPrimaryKey(ExecutionPlanDO record);
}