package com.performance.controller;

import com.performance.pojo.AppDO;
import com.performance.services.IAppService;
import com.performance.utils.BaseCPT;
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
public class AppController extends BaseCPT {

    @Autowired
    IAppService appService;

    @PostMapping("/list")
    public Result queryAppList(AppDO appDO) {
        return appService.queryAppList(appDO);
    }

    @PostMapping("/add")
    public Result addApp(AppDO appDO) {
        return appService.addApp(appDO);
    }

}
