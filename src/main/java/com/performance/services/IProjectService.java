package com.performance.services;

import com.performance.pojo.ProjectDO;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import com.performance.query.ProjectQuery;

/**
 * 创建时间: 2019/6/26 下午4:33
 * 类描述:
 *
 * @author lianyu
 */
public interface IProjectService {

    Result<PageBean<ProjectDO>> queryProjectList(ProjectQuery projectQuery, Integer index, Integer pageSize);

    Result addProject(ProjectDO projectDO);

    Result modifyProject(ProjectDO projectDO);

    Result removeProject(Long id);

}
