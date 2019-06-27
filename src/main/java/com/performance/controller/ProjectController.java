package com.performance.controller;

import com.performance.pojo.ProjectDO;
import com.performance.services.IProjectService;
import com.performance.utils.Result;
import com.performance.vo.ProjectVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间: 2019/6/26 下午5:39
 * 类描述:
 *
 * @author lianyu
 */
@RestController
@RequestMapping("/cpt/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @PostMapping("/list")
    public Result queryProject(ProjectVO projectVO) {
        return projectService.queryProjectList(projectVO);
    }

    @PostMapping("/add")
    public Result addProject(ProjectDO projectDO) {
        return projectService.addProject(projectDO);
    }

    @GetMapping("/remove")
    public Result removeProject(@Param("id") Long id) {
        return projectService.removeProject(id);
    }

    @PostMapping("/modify")
    public Result modifyProject(ProjectDO projectD) {
        return projectService.modifyProject(projectD);
    }

}
