package com.performance.services.impl;

import com.performance.dao.DevicesDOMapper;
import com.performance.enums.ResultEnum;
import com.performance.pojo.DevicesDO;
import com.performance.services.IDevicesService;
import com.performance.utils.BaseCPT;
import com.performance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-08-14 23:42
 * @Version 1.0
 **/
@Service
public class DevicesServiceImpl extends BaseCPT implements IDevicesService {

    @Autowired
    private DevicesDOMapper devicesDOMapper;

    @Override
    public Result addDevices(DevicesDO devicesDO) {
        //TODO 添加属性校验
        try {
            //TODO 底层SQL待修改
            int num = devicesDOMapper.insert(devicesDO);
            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

}
