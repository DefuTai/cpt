package com.performance.dao;

import com.performance.pojo.AppDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppDOMapper {

    /**
     * 通过ID查询指定APP信息
     *
     * @param id
     * @return
     */
    AppDO selectByPrimaryKey(Long id);

    /**
     * 根据条件查询APP信息
     *
     * @param appDO
     * @return
     */
    List<AppDO> selectApp(AppDO appDO);

    /**
     * 添加APP
     *
     * @param record
     * @return
     */
    int insert(AppDO record);

    /**
     * 添加APP（高级版）
     *
     * @param record
     * @return
     */
    int insertSelective(AppDO record);

    /**
     * 根据ID修改APP信息（高级版）
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AppDO record);

    /**
     * 根据ID修改APP信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(AppDO record);

    /**
     * 根据ID删除指定APP记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

}