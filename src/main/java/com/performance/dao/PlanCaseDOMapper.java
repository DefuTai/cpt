package com.performance.dao;

import com.performance.po.PlanCaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlanCaseDOMapper {

    /**
     * 通过执行计划ID获取已关联用例ID集合
     *
     * @param planId
     * @return
     */
    List<Long> selectCaseIdListByPlanId(@Param("planId") Long planId);

    /**
     * 通过用例ID获取已关联执行计划ID集合
     *
     * @param caseId
     * @return
     */
    List<Long> selectPlanIdListByCaseId(Long caseId);

    /**
     * 插入执行计划和测试用例关系
     *
     * @param record
     * @return
     */
    int insertPlanCase(PlanCaseDO record);

    /**
     * 根据执行计划ID删除关系表记录
     *
     * @param planIds
     * @return
     */
    int deleteByPlanIds(@Param("planIds") List<Long> planIds);

    /**
     * 根据执行计划ID和用例ID精确删除关系记录（适用于对已存在用例关联关系的修改）
     *
     * @param planId
     * @param caseIds
     * @return
     */
    int deleteByPlanIdAndCaseIds(@Param("planId") Long planId, @Param("caseIds") List<Long> caseIds);

}