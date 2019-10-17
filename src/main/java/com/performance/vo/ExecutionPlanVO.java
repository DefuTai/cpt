package com.performance.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-10-12 15:46
 * @Version 1.0
 **/
public class ExecutionPlanVO implements Serializable {

    private static final long serialVersionUID = 9145971857990404388L;

    /**
     * 执行计划ID
     */
    private Long id;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 描述
     */
    private String description;

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

    /**
     * 应用
     */
    private AppVO appVO;

    /**
     * 设备
     */
    private DevicesVO devicesVO;

    /**
     * 测试用例
     */
    private List<CaseVO> caseVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public AppVO getAppVO() {
        return appVO;
    }

    public void setAppVO(AppVO appVO) {
        this.appVO = appVO;
    }

    public DevicesVO getDevicesVO() {
        return devicesVO;
    }

    public void setDevicesVO(DevicesVO devicesVO) {
        this.devicesVO = devicesVO;
    }

    public List<CaseVO> getCaseVOList() {
        return caseVOList;
    }

    public void setCaseVOList(List<CaseVO> caseVOList) {
        this.caseVOList = caseVOList;
    }
}
