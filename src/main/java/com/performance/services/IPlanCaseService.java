package com.performance.services;

import com.performance.po.PlanCaseDO;
import com.performance.utils.Result;

import java.util.List;

/**
 * @Author 鲢鱼
 * @Data 2019-10-14 14:49
 * @Version 1.0
 **/
public interface IPlanCaseService {

    /**
     * 通过执行计划ID获取已关联用例ID集合
     *
     * @param planId
     * @return
     */
    List<Long> findCaseIdListByPlanId(Long planId);

    /**
     * 通过用例ID获取已关联执行计划ID集合
     *
     * @param caseId
     * @return
     */
    List<Long> findPlanIdListByCaseId(Long caseId);

    /**
     * 新增执行计划和测试用例关系记录
     *
     * @param planCaseDO
     * @return
     */
    Result addPlanCase(PlanCaseDO planCaseDO);

    /**
     * 删除执行计划和测试用例关系记录
     *
     * @param planIds
     * @return
     */
    Result removeByPlanIds(List<Long> planIds);

    /**
     * 根据执行计划ID和用例ID精准删除
     *
     * @param planId
     * @param caseIds
     * @return
     */
    Result removeByPlanIdAndCaseIds(Long planId, List<Long> caseIds);

}
