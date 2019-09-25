package com.performance.dao;

import com.performance.po.AppDO;
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
     * APP列表查询
     *
     * @param appDO
     * @return
     */
    List<AppDO> selectApp(AppDO appDO);

    /**
     * APP列表总数量
     *
     * @param appDO
     * @return
     */
    int selectAppCount(AppDO appDO);

    /**
     * 校验是否存在重复记录
     *
     * @param appDO
     * @return
     */
    List<AppDO> checkRepeat(AppDO appDO);

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