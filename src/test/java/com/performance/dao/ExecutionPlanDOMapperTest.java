package com.performance.dao;

import com.alibaba.fastjson.JSON;
import com.performance.po.ExecutionPlanDO;
import com.performance.query.ExecutionPlanQuery;
import com.performance.utils.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutionPlanDOMapperTest {

    @Autowired
    ExecutionPlanDOMapper executionPlanDOMapper;

    @Test
    public void testSelectByPrimaryKey() {
        ExecutionPlanQuery query = new ExecutionPlanQuery();
        query.setAppId(555514693750L);
        query.setDevicesId(555565986406196922L);
        query.setPlanName("");
        query.setTester("");

        List<ExecutionPlanDO> executionPlanDOList = executionPlanDOMapper.selectExecutionPlanList(query);
        int count = executionPlanDOMapper.selectExecutionPlanListCount(query);
        System.out.println("执行计划列表：" + JSON.toJSONString(executionPlanDOList));
        System.out.println("执行计划列表总记录数：" + count);
    }

    @Test
    public void testInsert() {
        ExecutionPlanDO executionPlanDO = new ExecutionPlanDO();
        executionPlanDO.setId(Long.valueOf(UuidUtil.getUuid()));
        executionPlanDO.setAppId(555514693750L);
        executionPlanDO.setDevicesId(555565986406196922L);
        executionPlanDO.setPlanName("测试计划01");
        executionPlanDO.setDescription("");
        executionPlanDO.setTester("鲢鱼");
        executionPlanDO.setStartTime("2019-10.12 11:51:00");
        executionPlanDO.setEndTime("2019-10.13 11:51:00");

        int row = executionPlanDOMapper.insertExecutionPlan(executionPlanDO);
        System.out.println("受影响行数：" + row);
    }

}