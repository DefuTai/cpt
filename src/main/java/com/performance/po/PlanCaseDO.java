package com.performance.po;

import com.performance.po.base.BaseDO;

/**
 * 测试计划和用例关系
 */
public class PlanCaseDO extends BaseDO {

    private static final long serialVersionUID = -5277518894821212343L;

    /**
     * id
     */
    private Long id;

    /**
     * 执行计划ID
     */
    private Long planId;

    /**
     * 用例ID
     */
    private Long caseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

}