package com.performance.dao;

import com.performance.po.ExecutionRecordDO;

public interface ExecutionRecordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExecutionRecordDO record);

    int insertSelective(ExecutionRecordDO record);

    ExecutionRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExecutionRecordDO record);

    int updateByPrimaryKey(ExecutionRecordDO record);
}