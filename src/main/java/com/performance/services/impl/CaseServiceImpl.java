package com.performance.services.impl;

import com.performance.BaseCPT;
import com.performance.dao.CaseDOMapper;
import com.performance.enums.ResultEnum;
import com.performance.pojo.CaseDO;
import com.performance.services.ICaseService;
import com.performance.utils.PageBean;
import com.performance.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间: 2019/6/24 下午10:41
 * 类描述:
 *
 * @author lianyu
 */
@Service
public class CaseServiceImpl extends BaseCPT implements ICaseService {

    protected static Logger logger = LoggerFactory.getLogger(CaseServiceImpl.class);

    @Autowired
    private CaseDOMapper caseDOMapper;

    @Override
    public Result<PageBean<CaseDO>> queryCaseList(CaseDO caseDO) {
        try {
            //获取列表数据
            List<CaseDO> caseList = caseDOMapper.selectCase(caseDO);
            //获取列表总记录数
            int count = caseDOMapper.selectCaseCount(caseDO);
            //分页
            PageBean<CaseDO> pageBean = new PageBean<>(caseList, count);

            result = resultUtil.success(pageBean);
        } catch (Exception e) {
            logger.error(ResultEnum.ERROR_UNKNOWN.getMsg(), e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result addCase(CaseDO caseDO) {
        try {
            //TODO 逻辑校验

            int num = caseDOMapper.insertSelective(caseDO);
            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error(ResultEnum.ERROR_UNKNOWN.getMsg(), e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result removeCase(Long id) {
        try {
            if (id != null) {
                CaseDO caseInfo = caseDOMapper.selectByPrimaryKey(id);
                if (caseInfo == null) {
                    return resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "当前记录不存在或已被删除，请刷新后重试！");
                }
            }

            int num = caseDOMapper.deleteByPrimaryKey(id);

            if (num > 0) {
                result = resultUtil.success();
            } else {
                result = resultUtil.error(ResultEnum.ERROR_CUSTOM.getCode(), "指定用例记录不存在或已被删除，请刷新后重试！");
            }
        } catch (Exception e) {
            logger.error(ResultEnum.ERROR_UNKNOWN.getMsg(), e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

    @Override
    public Result modifyCase(CaseDO caseDO) {
        try {
            int num = caseDOMapper.updateByPrimaryKey(caseDO);

            if (num > 0) {
                result = resultUtil.success();
            }
        } catch (Exception e) {
            logger.error(ResultEnum.ERROR_UNKNOWN.getMsg(), e);
            return resultUtil.error(ResultEnum.ERROR_UNKNOWN.getCode(), ResultEnum.ERROR_UNKNOWN.getMsg());
        }
        return result;
    }

}
