package com.performance.services.impl;

import com.alibaba.fastjson.JSON;
import com.performance.po.CaseDO;
import com.performance.services.ICaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CaseServiceImplTest {

    @Autowired
    ICaseService caseService;

    @Test
    public void testFindCaseListByIds() {
        List<Long> ids = new ArrayList<>();
//        ids.add(555554059989098896L);
//        ids.add(555578244145144559L);
        List<CaseDO> caseDOList = caseService.findCaseListByIds(ids);
        System.out.println("返回结果：" + JSON.toJSONString(caseDOList));
    }
}