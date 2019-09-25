package com.performance.po;

import com.performance.po.base.BaseDO;

/**
 * 用例执行记录
 */
public class ExecutionRecordDO extends BaseDO {

    private static final long serialVersionUID = -5101474022256765619L;

    /**
     * id
     */
    private Long id;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     *
     */
    private Long environmentId;

    private Long taskId;

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

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

}