package com.performance.services;

import com.performance.po.ExecutionPlanDO;
import com.performance.query.ExecutionPlanQuery;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import com.performance.vo.ExecutionPlanVO;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-10-12 17:28
 * @Version 1.0
 **/
public interface IExecutionPlanService {

    /**
     * 分页查询执行计划
     *
     * @param query
     * @return
     */
    Result<PageBean<ExecutionPlanDO>> queryExecutionPlanPage(ExecutionPlanQuery query);

    /**
     * 通过ID获取执行计划详情
     *
     * @param id
     * @return
     */
    Result<ExecutionPlanVO> queryExecutionPlanInfo(Long id);

    /**
     * 添加执行计划
     *
     * @param executionPlanVO
     * @return
     */
    Result addExecutionPlan(ExecutionPlanVO executionPlanVO);

    /**
     * 修改执行计划
     *
     * @param executionPlanVO
     * @return
     */
    Result addOrModifyExecutionPlan(ExecutionPlanVO executionPlanVO);

    /**
     * 删除执行计划
     *
     * @param id
     * @return
     */
    Result removeExecutionPlan(Long id);

}
