package com.performance.services.impl;

import com.performance.dao.AppDOMapper;
import com.performance.pojo.AppDO;
import com.performance.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建时间: 2019/6/17 上午10:57
 * 类描述:
 *
 * @author lianyu
 */
@Service
public class AppServiceImpl implements IAppService {

    @Autowired
    private AppDOMapper appDOMapper;

    @Override
    public int insert(AppDO record) {
        return appDOMapper.insert(record);
    }

    @Override
    public AppDO selectByPrimaryKey(Long id) {
        return appDOMapper.selectByPrimaryKey(id);
    }

}
