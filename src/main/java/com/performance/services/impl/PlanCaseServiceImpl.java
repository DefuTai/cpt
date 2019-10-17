package com.performance.services.impl;

import com.performance.BaseCPT;
import com.performance.dao.PlanCaseDOMapper;
import com.performance.enums.ResultEnum;
import com.performance.po.PlanCaseDO;
import com.performance.services.IPlanCaseService;
import com.performance.utils.Result;
import com.performance.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-10-14 14:51
 * @Version 1.0
 **/
@Service
public class PlanCaseServiceImpl extends BaseCPT implements IPlanCaseService {

    private static Logger logger = LoggerFactory.getLogger(PlanCaseServiceImpl.class);

    @Autowired
    PlanCaseDOMapper planCaseDOMapper;

    @Override
    public List<Long> findCaseIdListByPlanId(Long planId) {
        if (planId == null || planId == 0) {
            return new ArrayList<>();
        }
        return planCaseDOMapper.selectCaseIdListByPlanId(planId);
    }

    @Override
    public List<Long> findPlanIdListByCaseId(Long caseId) {
        if (caseId == null || caseId == 0) {
            return new ArrayList<>();
        }
        return planCaseDOMapper.selectPlanIdListByCaseId(caseId);
    }

    @Override
    public Result addPlanCase(PlanCaseDO planCaseDO) {
        try {
            if (planCaseDO == null || planCaseDO.getPlanId() == null || planCaseDO.getCaseId() == null) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "PlanId或CaseId" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            planCaseDO.setId(Long.valueOf(UuidUtil.getUuid()));

            int row = planCaseDOMapper.insertPlanCase(planCaseDO);
            if (row > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("插入执行计划和用例关系时异常：", e);
            result = resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
            throw e;
        }
        return result;
    }

    @Override
    public Result removeByPlanIds(List<Long> planIds) {
        try {
            if (planIds.isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "PlanId" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            int row = planCaseDOMapper.deleteByPlanIds(planIds);
            if (row >= 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("删除执行计划和用例关系时异常", e);
            result = resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
            throw e;
        }
        return result;
    }

    @Override
    public Result removeByPlanIdAndCaseIds(Long planId, List<Long> caseIds) {
        try {
            if (planId == null || planId == 0) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "PlanId" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (caseIds.isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "CaseId" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }

            int row = planCaseDOMapper.deleteByPlanIdAndCaseIds(planId, caseIds);
            if (row >= 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("删除执行计划和用例关系时异常", e);
            result = resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
            throw e;
        }
        return result;
    }

}
