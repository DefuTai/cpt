package com.performance.services;

import com.performance.pojo.ProjectDO;
import com.performance.query.ProjectQuery;
import com.performance.utils.PageBean;
import com.performance.utils.Result;

/**
 * 创建时间: 2019/6/26 下午4:33
 * 类描述:
 *
 * @author lianyu
 */
public interface IProjectService {

    /**
     * 分页获取项目列表
     *
     * @param projectQuery
     * @return
     */
    Result<PageBean<ProjectDO>> queryProjectList(ProjectQuery projectQuery);

    /**
     * 根据ID获取项目信息
     *
     * @return
     */
    Result<ProjectDO> queryProjectInfo(Long projectId);

    /**
     * 添加项目
     *
     * @param projectDO
     * @return
     */
    Result addProject(ProjectDO projectDO);

    /**
     * 修改项目
     *
     * @param projectDO
     * @return
     */
    Result modifyProject(ProjectDO projectDO);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    Result removeProject(Long id);

}
