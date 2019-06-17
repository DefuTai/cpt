package com.performance.services.impl;

import com.performance.pojo.AppDO;
import com.performance.services.IAppService;
import com.performance.utils.Result;
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
        appDO.setType("1");
        appDO.setUploader("鲢鱼");
        appDO.setRemarks("测试数据");
        appDO.setCreateTime(System.currentTimeMillis() + "");
        appDO.setModifyTime(System.currentTimeMillis() + "");

        Result result = appService.addApp(appDO);

        Assert.assertTrue(result.getCode().equals("1000"), "添加App失败！");
    }

    @Test
    public void testSelectByPrimaryKey() {
        AppDO appDO = new AppDO();
        appDO.setName("CashDesk");
        appDO.setVersion("5.8.5");
        appDO.setPackageName("com.zmsoft.eatery.cashdesk.activity");
        appDO.setType("1");
        appDO.setUploader("鲢鱼");
        appDO.setRemarks("测试数据");

        Result result = appService.queryAppList(appDO);
        Assert.assertNotNull(result.getData(), "查询失败，结果为空！");
    }
}