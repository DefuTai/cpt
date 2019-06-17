package com.performance.controller;

import com.performance.enums.ResultEnum;
import com.performance.pojo.AppDO;
import com.performance.services.IAppService;
import com.performance.utils.BaseController;
import com.performance.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间: 2019/6/17 上午11:23
 * 类描述:
 *
 * @author lianyu
 */
@RestController
@RequestMapping("/app")
public class AppController extends BaseController {

    @Autowired
    IAppService appService;

    @PostMapping("/get")
    public Result getApp(Long id) {
        try {
            AppDO appDO = appService.selectByPrimaryKey(id);
            if (appDO != null) {
                return resultUtil.success(appDO);
            } else {
                return resultUtil.error(ResultEnum.ERROR_IS_NULL.getCode(), ResultEnum.ERROR_IS_NULL.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
    }

    @PostMapping("/add")
    public Result addApp(AppDO appDO) {
        int num = appService.insert(appDO);
        if (num > 0) {
            return resultUtil.success();
        } else {
            return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
        }
    }

}
