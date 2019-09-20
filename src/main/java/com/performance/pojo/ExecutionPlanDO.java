package com.performance.pojo;

public class ExecutionPlanDO {
    // 执行计划ID
    private Long id;
    // 项目ID
    private Long project_id;
    // 应用ID
    private Long appId;
    // 设备ID
    private Long devicesId;
    // 线程数
    private Integer threads;
    // 执行次数
    private Integer frequency;
    // 执行间隔
    private Integer interval;
    // 永久执行（1.是；0.否；默认否）
    private Integer forever;
    // 是否有效（1，有效；0无效；默认有效）
    private Integer isValid;
    // 创建时间
    private String createTime;
    // 修改时间
    private String modifyTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getDevicesId() {
        return devicesId;
    }

    public void setDevicesId(Long devicesId) {
        this.devicesId = devicesId;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getForever() {
        return forever;
    }

    public void setForever(Integer forever) {
        this.forever = forever;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }
}