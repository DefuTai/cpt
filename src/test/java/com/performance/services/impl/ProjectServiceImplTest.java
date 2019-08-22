package com.performance.services.impl;

import com.alibaba.fastjson.JSON;
import com.performance.pojo.ProjectDO;
import com.performance.query.ProjectQuery;
import com.performance.services.IProjectService;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {

    static Logger logger = LoggerFactory.getLogger(DevicesServiceImplTest.class);

    @Autowired
    IProjectService projectService;

    @Test
    public void testQueryProjectList() {
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setName("xxx");
        Result<PageBean<ProjectDO>> projectList = projectService.queryProjectList(projectQuery, 1, 2);
        logger.info("返回结果：" + JSON.toJSONString(projectList.getData()));
    }
}