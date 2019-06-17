package com.performance.services;

import com.performance.pojo.AppDO;
import com.performance.utils.Result;

import java.util.List;

/**
 * 创建时间: 2019/6/17 上午10:49
 * 类描述:
 *
 * @author lianyu
 */
public interface IAppService {

    Result<List<AppDO>> queryAppList(AppDO appDO);

    Result addApp(AppDO record);

    Result removeApp(Long id);

}
