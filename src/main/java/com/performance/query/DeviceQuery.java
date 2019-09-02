package com.performance.query;

/**
 * 描述：设备查询条件
 *
 * @Author 鲢鱼
 * @Data 2019-08-18 14:51
 * @Version 1.0
 **/
public class DeviceQuery extends BaseQuery {

    //设备名称
    private String deviceName;

    //品牌
    private String brand;

    //系统类型（1.Android  2.iOS）
    private Integer systemType;

    //系统版本
    private String systemVersion;

    //分辨率
    private String resolution;

    //ADB连接状态（1。device(设备已连接), 0.offline（表示设备未连接成功或无响应）, -1.no device（没有设备/模拟器连接））
    private Integer connectStatus;

    //使用状态（0.空闲, 1.使用中）
    private Integer useStatus;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(Integer connectStatus) {
        this.connectStatus = connectStatus;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
}
