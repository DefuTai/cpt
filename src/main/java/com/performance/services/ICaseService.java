package com.performance.services;

import com.performance.pojo.CaseDO;
import com.performance.utils.PageBean;
import com.performance.utils.Result;

/**
 * 创建时间: 2019/6/24 下午10:39
 * 类描述:
 *
 * @author lianyu
 */
public interface ICaseService {

    Result<PageBean<CaseDO>> queryCaseList(CaseDO caseDO);

    Result addCase(CaseDO caseDO);

    Result removeCase(Long id);

    Result modifyCase(CaseDO caseDO);

}
