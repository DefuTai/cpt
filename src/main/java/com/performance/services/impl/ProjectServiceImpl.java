package com.performance.services.impl;

import com.performance.dao.ProjectDOMapper;
import com.performance.enums.ResultEnum;
import com.performance.pojo.ProjectDO;
import com.performance.services.IProjectService;
import com.performance.utils.BaseCPT;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import com.performance.vo.ProjectVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间: 2019/6/26 下午4:41
 * 类描述:
 *
 * @author lianyu
 */
@Service
public class ProjectServiceImpl extends BaseCPT implements IProjectService {

    @Autowired
    private ProjectDOMapper projectDOMapper;

    @Override
    public Result<PageBean<ProjectDO>> queryProjectList(ProjectVO projectVO) {
        try {
            List<ProjectDO> projectDOList = projectDOMapper.selectProject(projectVO);

            if (!projectDOList.isEmpty()) {
                int totalCount = projectDOMapper.selectProjectCounr(projectVO);
                PageBean<ProjectDO> pageBean = new PageBean<>(projectDOList, totalCount);
                result.setData(pageBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result addProject(ProjectDO projectDO) {
        try {
            if (StringUtils.isBlank(projectDO.getName())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[name]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (StringUtils.isBlank(projectDO.getTester())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[tester]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (StringUtils.isBlank(projectDO.getStartTime())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[startTime]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }
            if (StringUtils.isBlank(projectDO.getEndTime())) {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[endTime]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }

            int num = projectDOMapper.insertSelective(projectDO);

            if (num > 0) {
                result = resultUtil.success();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result modifyProject(ProjectDO projectDO) {
        try {
            int num = projectDOMapper.updateByPrimaryKeySelective(projectDO);

            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result removeProject(Long id) {
        try {
            if (id == null) {
                ProjectDO projectDO = projectDOMapper.selectByPrimaryKey(id);
                if (projectDO == null) {
                    return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "当前记录不存在或已被删除，请刷新后重试！");
                }
            } else {
                return resultUtil.error(ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getCode(), "[id]" + ResultEnum.ERROR_LACK_BUSINESS_PARAMETERS.getMsg());
            }

            //TODO 待补逻辑：如果项目已关联测试计划，不允许直接删除

            int num = projectDOMapper.deleteByPrimaryKey(id);

            if (num > 0) {
                result = resultUtil.success();
            } else {
                result = resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "指定APP记录不存在或已被删除，请刷新后重试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

}