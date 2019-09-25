package com.performance.query;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-09-23 18:04
 * @Version 1.0
 **/
public class CaseQuery extends BaseQuery {

    /**
     * 用例名称
     */
    private String name;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
