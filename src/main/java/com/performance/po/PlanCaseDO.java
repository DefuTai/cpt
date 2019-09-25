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
    private String id;

    /**
     * 执行计划ID
     */
    private String planId;

    /**
     * 用例ID
     */
    private String caseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

}