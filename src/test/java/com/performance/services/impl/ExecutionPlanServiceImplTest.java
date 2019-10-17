package com.performance.services.impl;

import com.alibaba.fastjson.JSON;
import com.performance.po.ExecutionPlanDO;
import com.performance.query.ExecutionPlanQuery;
import com.performance.services.IExecutionPlanService;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import com.performance.vo.AppVO;
import com.performance.vo.CaseVO;
import com.performance.vo.DevicesVO;
import com.performance.vo.ExecutionPlanVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ExecutionPlanServiceImplTest {

    @Autowired
    IExecutionPlanService executionPlanService;

    @Test
    public void testQueryExecutionPlanPage() {
        ExecutionPlanQuery query = new ExecutionPlanQuery();
        query.setIndex(1);
        query.setPageSize(50);

        Result<PageBean<ExecutionPlanDO>> executionPlanPage = executionPlanService.queryExecutionPlanPage(query);
        System.out.println("执行计划分页结果：" + JSON.toJSONString(executionPlanPage));
    }

    @Test
    public void testFindExecutionPlanDetail() {
        Long id = 555514330801852458L;
        Result<ExecutionPlanVO> result = executionPlanService.queryExecutionPlanInfo(id);
        System.out.println("获取执行计划及已关联用例集合：" + JSON.toJSONString(result.getData()));
    }

    @Test
    public void testAddExecutionPlan() {
        ExecutionPlanVO executionPlanVO = new ExecutionPlanVO();

        AppVO appVO = new AppVO();
        appVO.setId(55558820502L);
        executionPlanVO.setAppVO(appVO);

        DevicesVO devicesVO = new DevicesVO();
        devicesVO.setId(55551807276L);
        executionPlanVO.setDevicesVO(devicesVO);
        executionPlanVO.setPlanName("执行计划002");
        executionPlanVO.setDescription("接口调试～～");
        executionPlanVO.setTester("鲢鱼");
        executionPlanVO.setStartTime("2019-10-16 09:59:59");
        executionPlanVO.setEndTime("2019-10-17 09:59:59");

        List<CaseVO> caseVOList = new ArrayList<>();

        CaseVO caseVO1 = new CaseVO();
        caseVO1.setId(555554059989098896L);
        caseVOList.add(caseVO1);

        CaseVO caseVO2 = new CaseVO();
        caseVO2.setId(555578244145144559L);
        caseVOList.add(caseVO2);

        executionPlanVO.setCaseVOList(caseVOList);

        Result result = executionPlanService.addExecutionPlan(executionPlanVO);
        System.out.println("返回结果：" + JSON.toJSONString(result));
    }

    @Test
    @Rollback(false)
    public void testAddOrModifyExecutionPlan() {
        ExecutionPlanVO executionPlanVO = new ExecutionPlanVO();
        executionPlanVO.setId(555589862865953479L);

        AppVO appVO = new AppVO();
        appVO.setId(55558820502L);
        executionPlanVO.setAppVO(appVO);

        DevicesVO devicesVO = new DevicesVO();
        devicesVO.setId(55551807276L);
        executionPlanVO.setDevicesVO(devicesVO);

        executionPlanVO.setPlanName("执行计划002");
        executionPlanVO.setDescription("接口调试～～");
        executionPlanVO.setTester("鲢鱼");
        executionPlanVO.setStartTime("2019-10-16 09:59:59");
        executionPlanVO.setEndTime("2019-10-17 09:59:59");

        List<CaseVO> caseVOList = new ArrayList<>();

//        CaseVO caseVO1 = new CaseVO();
//        caseVO1.setId(555554059989098896L);
//        caseVOList.add(caseVO1);

        CaseVO caseVO2 = new CaseVO();
        caseVO2.setId(555578244145144559L);
        caseVOList.add(caseVO2);

        executionPlanVO.setCaseVOList(caseVOList);

        Result result = executionPlanService.addOrModifyExecutionPlan(executionPlanVO);
        System.out.println("返回结果：" + JSON.toJSONString(result));
    }

}