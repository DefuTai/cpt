package com.performance.services;

import com.performance.po.CaseDO;
import com.performance.query.CaseQuery;
import com.performance.utils.PageBean;
import com.performance.utils.Result;

import java.util.List;

/**
 * 创建时间: 2019/6/24 下午10:39
 * 类描述:
 *
 * @author lianyu
 */
public interface ICaseService {

    /**
     * 分页查询用例列表
     *
     * @param query
     * @return
     */
    Result<PageBean<CaseDO>> queryCasePage(CaseQuery query);

    /**
     * 通过ids获取CaseDO集合
     *
     * @param ids
     * @return
     */
    List<CaseDO> findCaseListByIds(List<Long> ids);

    /**
     * 添加用例
     *
     * @param caseDO
     * @return
     */
    Result addCase(CaseDO caseDO);

    /**
     * 删除用例
     *
     * @param id
     * @return
     */
    Result removeCase(Long id);

    /**
     * 修改用例
     *
     * @param caseDO
     * @return
     */
    Result modifyCase(CaseDO caseDO);

}
