package com.performance.dao;

import com.performance.pojo.CaseDO;

public interface CaseDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseDO record);

    int insertSelective(CaseDO record);

    CaseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseDO record);

    int updateByPrimaryKey(CaseDO record);
}