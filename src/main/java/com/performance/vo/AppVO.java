package com.performance.vo;

/**
 * 描述：App view object
 *
 * @Author 鲢鱼
 * @Data 2019-10-15 10:54
 * @Version 1.0
 **/
public class AppVO {

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
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
