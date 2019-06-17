package com.performance.dao;

import com.performance.pojo.PlanCaseDO;

public interface PlanCaseDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlanCaseDO record);

    int insertSelective(PlanCaseDO record);

    PlanCaseDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlanCaseDO record);

    int updateByPrimaryKey(PlanCaseDO record);
}