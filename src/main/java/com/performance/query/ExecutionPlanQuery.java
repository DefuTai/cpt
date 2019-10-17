package com.performance.query;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-10-12 16:01
 * @Version 1.0
 **/
public class ExecutionPlanQuery extends BaseQuery {

    /**
     * 执行计划ID
     */
    private Long id;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 设备ID
     */
    private Long devicesId;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 测试人
     */
    private String tester;

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

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
