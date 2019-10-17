package com.performance.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-10-12 15:48
 * @Version 1.0
 **/
public class CaseVO implements Serializable {

    private static final long serialVersionUID = 7588599894079040390L;

    /**
     * 用例ID
     */
    private Long id;

    /**
     * 用例名称
     */
    private String name;

    /**
     * 前置条件
     */
    private String precondition;

    /**
     * 测试步骤
     */
    private String step;

    /**
     * 脚本地址
     */
    private String scriptAddress;

    /**
     * 期望结果
     */
    private String desiredResult;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 执行计划
     */
    private List<ExecutionPlanVO> executionPlanVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getScriptAddress() {
        return scriptAddress;
    }

    public void setScriptAddress(String scriptAddress) {
        this.scriptAddress = scriptAddress;
    }

    public String getDesiredResult() {
        return desiredResult;
    }

    public void setDesiredResult(String desiredResult) {
        this.desiredResult = desiredResult;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ExecutionPlanVO> getExecutionPlanVOList() {
        return executionPlanVOList;
    }

    public void setExecutionPlanVOList(List<ExecutionPlanVO> executionPlanVOList) {
        this.executionPlanVOList = executionPlanVOList;
    }
}
