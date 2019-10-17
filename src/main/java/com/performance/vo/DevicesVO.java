package com.performance.vo;

/**
 * 描述：Devices view object
 *
 * @Author 鲢鱼
 * @Data 2019-10-15 10:54
 * @Version 1.0
 **/
public class DevicesVO {

    /**
     * 设备ID
     */
    private Long id;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 系统类型(1.Android, 2.iOS)
     */
    private Integer systemType;

    /**
     * 系统版本
     */
    private String systemVersion;

    /**
     * 核心数
     */
    private Integer core;

    /**
     * 内存大小
     */
    private String ram;

    /**
     * 型号
     */
    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
