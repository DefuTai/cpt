package com.performance.po;

import com.performance.po.base.BaseDO;

/**
 * 设备
 */
public class DevicesDO extends BaseDO {

    private static final long serialVersionUID = 3920511850400251768L;

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
     * 网络状态（0.有线, 1.Wi-Fi, 2.2G, 3.3G, 4.4G, 5.5G）
     */
    private Integer network;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 序列号，简称SN
     */
    private String serialNumber;

    /**
     * MAC地址
     */
    private String macAddress;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 分辨率
     */
    private String resolution;

    /**
     * ADB连接状态（1。device(设备已连接), 0.offline（表示设备未连接成功或无响应）, -1.no device（没有设备/模拟器连接））
     */
    private Integer connectStatus;

    /**
     * 使用状态（0.空闲, 1.使用中）
     */
    private Integer useStatus;


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
        this.deviceName = deviceName == null ? null : deviceName.trim();
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
        this.systemVersion = systemVersion == null ? null : systemVersion.trim();
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
        this.ram = ram == null ? null : ram.trim();
    }

    public Integer getNetwork() {
        return network;
    }

    public void setNetwork(Integer network) {
        this.network = network;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
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