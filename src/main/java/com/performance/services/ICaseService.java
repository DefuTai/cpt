package com.performance.services;

import com.performance.po.CaseDO;
import com.performance.query.CaseQuery;
import com.performance.utils.PageBean;
import com.performance.utils.Result;

/**
 * 创建时间: 2019/6/24 下午10:39
 * 类描述:
 *
 * @author lianyu
 */
public interface ICaseService {

    Result<PageBean<CaseDO>> queryCaseList(CaseQuery query);

    Result addCase(CaseDO caseDO);

    Result removeCase(Long id);

    Result modifyCase(CaseDO caseDO);

}
