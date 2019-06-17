package com.performance.services.impl;

import com.google.gson.Gson;
import com.performance.pojo.AppDO;
import com.performance.services.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 创建时间: 2019/6/17 上午10:59
 * 类描述:
 *
 * @author lianyu
 */
public class AppServiceImplTest {

    @Autowired
    private IAppService appService;

    @Test
    public void testInsert() {
        AppDO appDO = new AppDO();
        appDO.setId(1L);
        appDO.setName("CashDesk");
        appDO.setVersion("5.8.5");
        appDO.setPackageName("com.zmsoft.eatery.cashdesk.activity");
        appDO.setIsValid((byte) 1);
        appDO.setSysType("1");
        appDO.setUploader("鲢鱼");
        appDO.setRemarks("测试数据");
        appDO.setCreateTime(System.currentTimeMillis() + "");
        appDO.setModifyTime(System.currentTimeMillis() + "");

        int count = appService.insert(appDO);

        Assert.assertTrue(count > 0, "添加App失败！");
    }

    @Test
    public void testSelectByPrimaryKey() {
        AppDO appDO = appService.selectByPrimaryKey(1L);
        System.out.println(new Gson().toJson(appDO));
    }
}