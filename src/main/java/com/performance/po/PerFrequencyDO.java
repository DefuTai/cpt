package com.performance.po;

import com.performance.po.base.BaseDO;

/**
 * 性能获取频率
 */
public class PerFrequencyDO extends BaseDO {

    private static final long serialVersionUID = -4261457739387737274L;

    /**
     * id
     */
    private Long id;

    /**
     * 执行计划ID
     */
    private Long planId;

    /**
     * 获取次数
     */
    private String frequency;

    /**
     * 间隔时间
     */
    private String interval;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;


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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval == null ? null : interval.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

}