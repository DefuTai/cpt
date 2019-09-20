package com.performance.dao;

import com.performance.pojo.ProjectDO;
import com.performance.query.ProjectQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectDOMapper {

    /**
     * 根据ID获取项目基本信息
     *
     * @param id
     * @return
     */
    ProjectDO selectByPrimaryKey(Long id);

    /**
     * 分页获取项目列表
     *
     * @param projectQuery
     * @return
     */
    List<ProjectDO> selectProjectList(ProjectQuery projectQuery);

    /**
     * 列表总记录数
     *
     * @param projectQuery
     * @return
     */
    int selectProjectListCount(ProjectQuery projectQuery);

    /**
     * 新增项目
     *
     * @param record
     * @return
     */
    int insertSelective(ProjectDO record);

    /**
     * 更新项目基本信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ProjectDO record);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

}