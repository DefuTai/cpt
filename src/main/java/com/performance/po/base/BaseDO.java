package com.performance.po.base;

import java.io.Serializable;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-09-23 15:10
 * @Version 1.0
 **/
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1977649216330168695L;

    /**
     * 是否有效（1，有效；0无效；默认有效）
     */
    private Integer isValid;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

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
