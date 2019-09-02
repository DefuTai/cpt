package com.performance.query;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-09-01 17:44
 * @Version 1.0
 **/
public class BaseQuery {

    protected Integer index = 1;
    protected Integer pageSize = 10;

    public Integer getIndex() {
        return pageSize * (index - 1);
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
