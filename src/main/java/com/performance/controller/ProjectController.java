package com.performance.controller;

import com.performance.BaseCPT;
import com.performance.pojo.ProjectDO;
import com.performance.query.ProjectQuery;
import com.performance.services.IProjectService;
import com.performance.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019/6/26 下午5:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/cpt/project")
public class ProjectController extends BaseCPT {

    @Autowired
    private IProjectService projectService;

    @PostMapping("/list")
    public Result queryProject(ProjectQuery projectQuery) {
        return projectService.queryProjectList(projectQuery);
    }

    @PostMapping("/edit")
    public Result addProject(ProjectDO projectDO) {
        if (null == projectDO.getId() || 0 == projectDO.getId()) {
            result = projectService.addProject(projectDO);
        } else {
            result = projectService.modifyProject(projectDO);
        }
        return result;
    }

    @GetMapping("/remove")
    public Result removeProject(@Param("id") Long id) {
        return projectService.removeProject(id);
    }

}
