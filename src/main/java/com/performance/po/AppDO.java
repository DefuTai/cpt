package com.performance.po;

import com.performance.po.base.BaseDO;

/**
 * 应用
 */
public class AppDO extends BaseDO {

    private static final long serialVersionUID = -6427806773962137420L;

    /**
     * ID
     */
    private Long id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 版本号
     */
    private String version;

    /**
     * 应用包名
     */
    private String packageName;

    /**
     * 应用类型
     */
    private String type;

    /**
     * 上传人
     */
    private String uploader;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 安装包地址
     */
    private String packageAddress;


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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getPackageAddress() {
        return packageAddress;
    }

    public void setPackageAddress(String packageAddress) {
        this.packageAddress = packageAddress;
    }

}