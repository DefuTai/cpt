package com.performance.dao;

import com.performance.po.CaseDO;
import com.performance.query.CaseQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CaseDOMapper {

    /**
     * 通过ID获取Case信息
     *
     * @param id
     * @return
     */
    CaseDO selectByPrimaryKey(Long id);

    /**
     * Case列表查询
     *
     * @param query
     * @return
     */
    List<CaseDO> selectCase(CaseQuery query);

    /**
     * Case列表记录总数量
     *
     * @param query
     * @return
     */
    int selectCaseCount(CaseQuery query);

    /**
     * 通过ids获取caseList
     *
     * @param ids
     * @return
     */
    List<CaseDO> selectCaseListByIds(@Param("ids") List<Long> ids);

    /**
     * 添加Case
     *
     * @param record
     * @return
     */
    int insert(CaseDO record);

    /**
     * 修改Case
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CaseDO record);

    /**
     * 删除Case
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

}