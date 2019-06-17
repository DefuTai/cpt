package com.performance.services.impl;

import com.performance.dao.AppDOMapper;
import com.performance.enums.ResultEnum;
import com.performance.pojo.AppDO;
import com.performance.services.IAppService;
import com.performance.utils.BaseCPT;
import com.performance.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间: 2019/6/17 上午10:57
 * 类描述:
 *
 * @author lianyu
 */
@Service
public class AppCPTImpl extends BaseCPT implements IAppService {

    @Autowired
    private AppDOMapper appDOMapper;


    @Override
    public Result<List<AppDO>> queryAppList(AppDO appDO) {
        List<AppDO> appList = null;
        try {
            appList = appDOMapper.selectApp(appDO);
            result.setData(appList);
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result addApp(AppDO appDO) {
        try {
            if (StringUtils.isBlank(appDO.getName())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg() + "[name]");
            }
            if (StringUtils.isBlank(appDO.getPackageName())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg() + "[packageName]");
            }
            if (StringUtils.isBlank(appDO.getType())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg() + "[type]");
            }
            if (StringUtils.isBlank(appDO.getVersion())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg() + "[version]");
            }

            //TODO id自动生成规则待修改
//            appDO.setId(Long.valueOf(UuidUtil.getUUID()));

            int num = appDOMapper.insert(appDO);

            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result removeApp(Long id) {
        try {
            int num = appDOMapper.deleteByPrimaryKey(id);

            if (num > 0) {
                result = resultUtil.success();
            } else {
                result = resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "指定APP记录不存在或已被删除，请刷新后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }
}
