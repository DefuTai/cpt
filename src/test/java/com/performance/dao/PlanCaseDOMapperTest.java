package com.performance.dao;

import com.performance.po.PlanCaseDO;
import com.performance.utils.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanCaseDOMapperTest {

    @Autowired
    PlanCaseDOMapper planCaseDOMapper;

    @Test
    public void testInsert() {
        PlanCaseDO planCaseDO = new PlanCaseDO();
        planCaseDO.setId(Long.valueOf(UuidUtil.getUuid()));
        planCaseDO.setPlanId(555514330801852458L);
        planCaseDO.setCaseId(555578244145144559L);

        int row = planCaseDOMapper.insertPlanCase(planCaseDO);
        System.out.println("受影响行数：" + row);
    }
}