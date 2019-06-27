package com.performance.dao;

import com.performance.pojo.ProjectDO;
import com.performance.vo.ProjectVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectDOMapper {

    ProjectDO selectByPrimaryKey(Long id);

    List<ProjectDO> selectProject(ProjectVO projectVO);

    int selectProjectCounr(ProjectVO projectVO);

    int insertSelective(ProjectDO record);

    int updateByPrimaryKeySelective(ProjectDO record);

    int deleteByPrimaryKey(Long id);

}