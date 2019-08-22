package com.performance.pojo;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-21 17:54
 * @Version 1.0
 **/
public class BaseObject {

    protected Integer index = 1;
    protected Integer pageSize = 2;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
