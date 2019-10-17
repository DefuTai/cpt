package com.performance.services.impl;

import com.performance.BaseCPT;
import com.performance.dao.ExecutionPlanDOMapper;
import com.performance.enums.ResultEnum;
import com.performance.po.*;
import com.performance.query.ExecutionPlanQuery;
import com.performance.services.*;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import com.performance.utils.UuidUtil;
import com.performance.vo.AppVO;
import com.performance.vo.CaseVO;
import com.performance.vo.DevicesVO;
import com.performance.vo.ExecutionPlanVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-10-12 17:35
 * @Version 1.0
 **/
@Service
public class ExecutionPlanServiceImpl extends BaseCPT implements IExecutionPlanService {

    private static Logger logger = LoggerFactory.getLogger(ExecutionPlanServiceImpl.class);

    @Autowired
    IAppService appService;
    @Autowired
    ICaseService caseService;
    @Autowired
    IDevicesService devicesService;
    @Autowired
    IPlanCaseService planCaseService;

    @Autowired
    ExecutionPlanDOMapper executionPlanDOMapper;

    @Override
    public Result<PageBean<ExecutionPlanDO>> queryExecutionPlanPage(ExecutionPlanQuery query) {
        PageBean<ExecutionPlanDO> executionPlanDOPageBean;

        try {
            List<ExecutionPlanDO> list = executionPlanDOMapper.selectExecutionPlanList(query);
            int count = executionPlanDOMapper.selectExecutionPlanListCount(query);

            executionPlanDOPageBean = new PageBean<>(list, count);
            executionPlanDOPageBean.setCurrentPage(query.getIndex());
            executionPlanDOPageBean.setPageSize(query.getPageSize());
        } catch (Exception e) {
            logger.error("分页获取执行计划列表异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return resultUtil.success(executionPlanDOPageBean);
    }

    @Override
    public Result<ExecutionPlanVO> queryExecutionPlanInfo(Long id) {
        if (id == null || id == 0) {
            return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "执行计划ID" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
        }

        ExecutionPlanVO executionPlanVO = new ExecutionPlanVO();

        // 通过执行计划ID获取执行计划基本信息
        ExecutionPlanDO executionPlanDO = executionPlanDOMapper.selectByPrimaryKey(id);
        if (executionPlanDO != null) {
            BeanUtils.copyProperties(executionPlanDO, executionPlanVO);

            // 通过AppId获取App信息
            Result<AppDO> appDOResult = appService.findAppById(executionPlanDO.getAppId());
            AppVO appVO = new AppVO();
            if (appDOResult.getData() != null) {
                AppDO appDO = appDOResult.getData();
                BeanUtils.copyProperties(appDO, appVO);
            }
            executionPlanVO.setAppVO(appVO);

            // 通过devicesId获取Devices信息
            Result<DevicesDO> devicesDOResult = devicesService.findDeviceById(executionPlanDO.getDevicesId());
            DevicesVO devicesVO = new DevicesVO();
            if (devicesDOResult.getData() != null) {
                DevicesDO devicesDO = devicesDOResult.getData();
                BeanUtils.copyProperties(devicesDO, devicesVO);
            }
            executionPlanVO.setDevicesVO(devicesVO);

            // 通过执行计划ID获取所有PlanCase记录
            List<Long> caseIdList = planCaseService.findCaseIdListByPlanId(executionPlanDO.getId());
            // 通过关系表中记录的caseId，查询用例信息
            List<CaseDO> caseDOList = caseService.findCaseListByIds(caseIdList);
            List<CaseVO> caseVOList = new ArrayList<>();
            for (CaseDO caseDO : caseDOList) {
                CaseVO caseVO = new CaseVO();
                BeanUtils.copyProperties(caseDO, caseVO);
                caseVOList.add(caseVO);
            }
            executionPlanVO.setCaseVOList(caseVOList);
        }

        return resultUtil.success(executionPlanVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addExecutionPlan(ExecutionPlanVO executionPlanVO) {
        try {
            if (executionPlanVO == null) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            Long appId = executionPlanVO.getAppVO().getId();
            if (appId == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_APP_ID_NULL.getCode(), ResultEnum.ERROR_EXE_SERVICE_APP_ID_NULL.getMsg());
            }
            Long devicesId = executionPlanVO.getDevicesVO().getId();
            if (devicesId == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_DEVICE_ID_NULL.getCode(), ResultEnum.ERROR_EXE_SERVICE_DEVICE_ID_NULL.getMsg());
            }
            if (executionPlanVO.getCaseVOList().isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_CASE_LIST_NULL.getCode(), ResultEnum.ERROR_EXE_SERVICE_CASE_LIST_NULL.getMsg());
            }

            Result<AppDO> appDOResult = appService.findAppById(appId);
            if (appDOResult.getData() == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_APP_NOT_EXIST.getCode(), ResultEnum.ERROR_EXE_SERVICE_APP_NOT_EXIST.getMsg());
            }

            Result<DevicesDO> devicesDOResult = devicesService.findDeviceById(devicesId);
            if (devicesDOResult.getData() == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_DEVICES_NOT_EXIST.getCode(), ResultEnum.ERROR_EXE_SERVICE_DEVICES_NOT_EXIST.getMsg());
            }

            List<CaseVO> caseVOList = executionPlanVO.getCaseVOList();
            List<Long> caseIdList = new ArrayList<>();
            for (CaseVO caseVO : caseVOList) {
                caseIdList.add(caseVO.getId());
            }
            List<CaseDO> caseDOList = caseService.findCaseListByIds(caseIdList);
            if (caseDOList.isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_CASE_NOT_EXIST.getCode(), ResultEnum.ERROR_EXE_SERVICE_CASE_NOT_EXIST.getMsg());
            }

            ExecutionPlanDO executionPlanDO = new ExecutionPlanDO();

            BeanUtils.copyProperties(executionPlanVO, executionPlanDO);

            executionPlanDO.setId(Long.valueOf(UuidUtil.getUuid()));
            executionPlanDO.setAppId(appId);
            executionPlanDO.setDevicesId(devicesId);

            // 保存执行计划信息
            executionPlanDOMapper.insertExecutionPlan(executionPlanDO);

            // 保存执行计划和用例关系
            for (CaseDO caseDO : caseDOList) {
                PlanCaseDO planCaseDO = new PlanCaseDO();
                planCaseDO.setPlanId(executionPlanDO.getId());
                planCaseDO.setCaseId(caseDO.getId());

                planCaseService.addPlanCase(planCaseDO);
            }
        } catch (Exception e) {
            logger.error("保存执行计划异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return resultUtil.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addOrModifyExecutionPlan(ExecutionPlanVO executionPlanVO) {
        try {
            if (executionPlanVO == null) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (executionPlanVO.getId() == null) {
                executionPlanVO.setId(Long.valueOf(UuidUtil.getUuid()));
            }
            Long appId = executionPlanVO.getAppVO().getId();
            if (appId == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_APP_ID_NULL.getCode(), ResultEnum.ERROR_EXE_SERVICE_APP_ID_NULL.getMsg());
            }
            Long devicesId = executionPlanVO.getDevicesVO().getId();
            if (devicesId == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_DEVICE_ID_NULL.getCode(), ResultEnum.ERROR_EXE_SERVICE_DEVICE_ID_NULL.getMsg());
            }
            if (executionPlanVO.getCaseVOList().isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_CASE_LIST_NULL.getCode(), ResultEnum.ERROR_EXE_SERVICE_CASE_LIST_NULL.getMsg());
            }

            Result<AppDO> appDOResult = appService.findAppById(appId);
            if (appDOResult.getData() == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_APP_NOT_EXIST.getCode(), ResultEnum.ERROR_EXE_SERVICE_APP_NOT_EXIST.getMsg());
            }

            Result<DevicesDO> devicesDOResult = devicesService.findDeviceById(devicesId);
            if (devicesDOResult.getData() == null) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_DEVICES_NOT_EXIST.getCode(), ResultEnum.ERROR_EXE_SERVICE_DEVICES_NOT_EXIST.getMsg());
            }

            List<CaseVO> caseVOList = executionPlanVO.getCaseVOList();
            List<Long> caseIdList = new ArrayList<>();
            for (CaseVO caseVO : caseVOList) {
                caseIdList.add(caseVO.getId());
            }
            List<CaseDO> caseDOList = caseService.findCaseListByIds(caseIdList);
            if (caseDOList.isEmpty()) {
                return resultUtil.error(ResultEnum.ERROR_EXE_SERVICE_CASE_NOT_EXIST.getCode(), ResultEnum.ERROR_EXE_SERVICE_CASE_NOT_EXIST.getMsg());
            }

            ExecutionPlanDO executionPlanDO = new ExecutionPlanDO();

            BeanUtils.copyProperties(executionPlanVO, executionPlanDO);

            executionPlanDO.setAppId(appId);
            executionPlanDO.setDevicesId(devicesId);

            // 保存执行计划信息
            int rows = executionPlanDOMapper.insertOrUpdateExecutionPlan(executionPlanDO);

            Long exePlanId = executionPlanDO.getId();

            switch (rows) {
                case 1:
                    for (CaseDO caseDO : caseDOList) {
                        PlanCaseDO planCaseDO = new PlanCaseDO();
                        planCaseDO.setPlanId(exePlanId);
                        planCaseDO.setCaseId(caseDO.getId());

                        planCaseService.addPlanCase(planCaseDO);
                    }
                    break;
                case 2:
                    List<Long> originalCaseIdList = planCaseService.findCaseIdListByPlanId(exePlanId);

                    Collection $_a = new ArrayList(originalCaseIdList);
                    Collection $_b = new ArrayList(caseIdList);

                    $_a.removeAll($_b);
                    List<Long> removeCaseIdList = new ArrayList<>($_a);
                    if (!removeCaseIdList.isEmpty()) {
                        // 删除修改后未勾选的用例
                        planCaseService.removeByPlanIdAndCaseIds(exePlanId, removeCaseIdList);
                    }

                    Collection $_c = new ArrayList(originalCaseIdList);
                    Collection $_d = new ArrayList(caseIdList);

                    $_d.removeAll($_c);
                    List<Long> addCaseIdList = new ArrayList<>($_d);
                    if (!addCaseIdList.isEmpty()) {
                        for (Long caseId : addCaseIdList) {
                            PlanCaseDO planCaseDO = new PlanCaseDO();
                            planCaseDO.setPlanId(exePlanId);
                            planCaseDO.setCaseId(caseId);

                            // 添加修改后新增加的用例
                            planCaseService.addPlanCase(planCaseDO);
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            logger.error("保存执行计划异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return resultUtil.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result removeExecutionPlan(Long id) {
        try {
            if (id == null || id == 0) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "执行计划ID" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }

            ExecutionPlanDO executionPlanDO = executionPlanDOMapper.selectByPrimaryKey(id);
            if (executionPlanDO == null) {
                return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "该数据不存在，请确认后重试！");
            }

            List<Long> caseIdList = planCaseService.findCaseIdListByPlanId(id);
            if (!caseIdList.isEmpty()) {
                List<Long> planIds = new ArrayList<>();
                planIds.add(id);

                // 删除执行计划和用例关系
                planCaseService.removeByPlanIds(planIds);
            }

            // 删除执计划
            int row = executionPlanDOMapper.deleteByPrimaryKey(id);
            if (row > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error("删除执行计划异常：", e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

}
