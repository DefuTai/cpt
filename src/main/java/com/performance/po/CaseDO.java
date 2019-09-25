package com.performance.po;

import com.performance.po.base.BaseDO;

/**
 * 用例
 */
public class CaseDO extends BaseDO {

    private static final long serialVersionUID = 5028660347781622598L;

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
        this.name = name == null ? null : name.trim();
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition == null ? null : precondition.trim();
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    public String getScriptAddress() {
        return scriptAddress;
    }

    public void setScriptAddress(String scriptAddress) {
        this.scriptAddress = scriptAddress == null ? null : scriptAddress.trim();
    }

    public String getDesiredResult() {
        return desiredResult;
    }

    public void setDesiredResult(String desiredResult) {
        this.desiredResult = desiredResult == null ? null : desiredResult.trim();
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

}