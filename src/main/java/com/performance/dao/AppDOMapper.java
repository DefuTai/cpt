package com.performance.dao;

import com.performance.pojo.AppDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppDO record);

    int insertSelective(AppDO record);

    AppDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppDO record);

    int updateByPrimaryKey(AppDO record);
}