package com.performance.po;

import com.performance.po.base.BaseDO;

/**
 * 性能信息表
 */
public class PerformanceDO extends BaseDO {

    private static final long serialVersionUID = 7510269081069829512L;

    /**
     * id
     */
    private Long id;

    /**
     * 性能获取频率ID
     */
    private Long frequencyId;

    /**
     * 执行计划ID
     */
    private Long executionPlanId;

    /**
     * 数据类型（1.CPU；2.内存；3.流量；4.电量；5.温度）
     */
    private String type;

    /**
     * 值
     */
    private String value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Long frequencyId) {
        this.frequencyId = frequencyId;
    }

    public Long getExecutionPlanId() {
        return executionPlanId;
    }

    public void setExecutionPlanId(Long executionPlanId) {
        this.executionPlanId = executionPlanId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}