package com.performance.dao;

import com.performance.pojo.CaseDO;
import org.apache.ibatis.annotations.Mapper;
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
     * @param caseDO
     * @return
     */
    List<CaseDO> selectCase(CaseDO caseDO);

    /**
     * Case列表记录总数量
     *
     * @param caseDO
     * @return
     */
    int selectCaseCount(CaseDO caseDO);

    /**
     * 添加Case
     *
     * @param record
     * @return
     */
    int insert(CaseDO record);

    /**
     * 添加Case（高级版）
     *
     * @param record
     * @return
     */
    int insertSelective(CaseDO record);

    /**
     * 修改Case
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CaseDO record);

    /**
     * 修改Case（高级版）
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(CaseDO record);

    /**
     * 删除Case
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

}