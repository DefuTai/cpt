package com.performance.controller;

import com.performance.BaseCPT;
import com.performance.po.AppDO;
import com.performance.services.IAppService;
import com.performance.utils.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间: 2019/6/17 上午11:23
 * 类描述:
 *
 * @author lianyu
 */
@ApiModel("APP管理")
@RestController
@RequestMapping("/cpt/app")
public class AppController extends BaseCPT {

    @Autowired
    IAppService appService;

    @ApiOperation("APP列表")
    @PostMapping("/list")
    public Result queryAppList(AppDO appDO) {
        return appService.queryAppList(appDO);
    }

    @ApiOperation("添加APP")
    @PostMapping("/add")
    public Result addApp(AppDO appDO) {
        return appService.addApp(appDO);
    }

    @ApiOperation("删除APP")
    @GetMapping("/remove")
    public Result removeApp(@Param("id") Long id) {
        return appService.removeApp(id);
    }

    @ApiOperation("修改APP")
    @PostMapping("/modify")
    public Result modifyApp(AppDO appDO) {
        return appService.modifyApp(appDO);
    }

}
