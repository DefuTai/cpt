package com.performance.dao;

import com.performance.pojo.ProjectDO;

public interface ProjectDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectDO record);

    int insertSelective(ProjectDO record);

    ProjectDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectDO record);

    int updateByPrimaryKey(ProjectDO record);
}