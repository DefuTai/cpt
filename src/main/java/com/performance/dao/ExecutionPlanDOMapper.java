package com.performance.dao;

import com.performance.po.ExecutionPlanDO;
import com.performance.query.ExecutionPlanQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExecutionPlanDOMapper {

    /**
     * 根据ID获取执行计划信息
     *
     * @param id
     * @return
     */
    ExecutionPlanDO selectByPrimaryKey(Long id);

    /**
     * 执行计划列表
     *
     * @param query
     * @return
     */
    List<ExecutionPlanDO> selectExecutionPlanList(ExecutionPlanQuery query);

    /**
     * 执行计划列表总记录数
     *
     * @param query
     * @return
     */
    int selectExecutionPlanListCount(ExecutionPlanQuery query);

    /**
     * 添加执行计划
     *
     * @param record
     * @return
     */
    int insertExecutionPlan(ExecutionPlanDO record);

    /**
     * 存在则更新，不存在则添加
     *
     * @param record
     * @return
     */
    int insertOrUpdateExecutionPlan(ExecutionPlanDO record);

    /**
     * 更新执行计划
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ExecutionPlanDO record);

    /**
     * 根据ID删除执行计划
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

}