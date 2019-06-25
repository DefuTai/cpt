package com.performance.controller;

import com.performance.pojo.CaseDO;
import com.performance.services.ICaseService;
import com.performance.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间: 2019/6/24 下午10:37
 * 类描述:
 *
 * @author lianyu
 */
@RestController
@RequestMapping("/cpt/case")
public class CaseController {

    @Autowired
    ICaseService caseService;

    @PostMapping("/list")
    public Result queryAppList(CaseDO caseDO) {
        return caseService.queryCaseList(caseDO);
    }

    @PostMapping("/add")
    public Result addApp(CaseDO caseDO) {
        return caseService.addCase(caseDO);
    }

    @GetMapping("/remove")
    public Result removeApp(@Param("id") Long id) {
        return caseService.removeCase(id);
    }

    @PostMapping("/modify")
    public Result modifyApp(CaseDO caseDO) {
        return caseService.modifyCase(caseDO);
    }

}
