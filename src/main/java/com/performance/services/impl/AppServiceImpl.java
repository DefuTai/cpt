package com.performance.services.impl;

import com.performance.BaseCPT;
import com.performance.dao.AppDOMapper;
import com.performance.enums.ResultEnum;
import com.performance.po.AppDO;
import com.performance.services.IAppService;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import com.performance.utils.UuidUtil;
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
public class AppServiceImpl extends BaseCPT implements IAppService {

    @Autowired
    private AppDOMapper appDOMapper;

    @Override
    public Result<PageBean<AppDO>> queryAppList(AppDO appDO) {
        PageBean<AppDO> page = null;
        try {
            //获取列表数据
            List<AppDO> appList = appDOMapper.selectApp(appDO);
            //获取列表总记录数
            int count = appDOMapper.selectAppCount(appDO);
            //分页
            page = new PageBean<>(appList, count);
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return resultUtil.success(page);
    }

    @Override
    public Result<AppDO> findAppById(Long appId) {
        if (appId == null || appId == 0) {
            return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "参数appId" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
        }
        AppDO appDO = appDOMapper.selectByPrimaryKey(appId);
        return resultUtil.success(appDO);
    }

    @Override
    public Result addApp(AppDO appDO) {
        try {
            if (StringUtils.isBlank(appDO.getName())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[name]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (StringUtils.isBlank(appDO.getPackageName())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[packageName]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (StringUtils.isBlank(appDO.getType())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[type]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (StringUtils.isBlank(appDO.getVersion())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[version]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }

            appDO.setId(Long.valueOf(UuidUtil.getUuid()));

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
            if (id != null) {
                AppDO app = appDOMapper.selectByPrimaryKey(id);
                if (app == null) {
                    return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "当前记录不存在或已被删除，请刷新后重试！");
                }
            }

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

    @Override
    public Result modifyApp(AppDO appDO) {
        try {
            if (appDO.getId() != null) {
                AppDO app = appDOMapper.selectByPrimaryKey(appDO.getId());
                if (app == null) {
                    return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "当前记录不存在或已被删除，请刷新后重试！");
                }
            }

            List<AppDO> appList = appDOMapper.checkRepeat(appDO);

            if (appList.size() > 0) {
                return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "相同类型和版本的APP已存在，请修改部分信息后重试！");
            }

            int num = appDOMapper.updateByPrimaryKey(appDO);

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
