package com.performance.controller;

import com.performance.BaseCPT;
import com.performance.query.ExecutionPlanQuery;
import com.performance.services.IExecutionPlanService;
import com.performance.utils.Result;
import com.performance.vo.ExecutionPlanVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-10-15 15:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cpt/plan")
public class ExecutionPlanController extends BaseCPT {

    @Autowired
    IExecutionPlanService executionPlanService;

    @PostMapping("/list")
    public Result findExecutionPlanPage(@Param("query") ExecutionPlanQuery query) {
        return executionPlanService.queryExecutionPlanPage(query);
    }

    @PostMapping("/detail")
    public Result findExecutionPlanDetail(@Param("id") Long id) {
        return executionPlanService.queryExecutionPlanInfo(id);
    }

    @PostMapping("/add")
    public Result addExecutionPlan(@Param("executionPlanVO") ExecutionPlanVO executionPlanVO) {
        return executionPlanService.addExecutionPlan(executionPlanVO);
    }

    @PostMapping("/modify")
    public Result addOrModifyExecutionPlan(@Param("executionPlanVO") ExecutionPlanVO executionPlanVO) {
        return executionPlanService.addOrModifyExecutionPlan(executionPlanVO);
    }

    @PostMapping("/remove")
    public Result removeExecutionPlan(@Param("id") Long id) {
        return executionPlanService.removeExecutionPlan(id);
    }

}
