package com.performance.dao;

import com.performance.pojo.ProjectDO;
import com.performance.query.ProjectQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectDOMapper {

    ProjectDO selectByPrimaryKey(Long id);

    List<ProjectDO> selectProjectList(ProjectDO projectDO);

    int selectProjectListCount(ProjectDO projectDO);

    int insertSelective(ProjectDO record);

    int updateByPrimaryKeySelective(ProjectDO record);

    int deleteByPrimaryKey(Long id);

}