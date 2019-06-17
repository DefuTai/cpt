package com.performance.services;

import com.performance.pojo.AppDO;

/**
 * 创建时间: 2019/6/17 上午10:49
 * 类描述:
 *
 * @author lianyu
 */
public interface IAppService {

    int insert(AppDO record);

    AppDO selectByPrimaryKey(Long id);

}
