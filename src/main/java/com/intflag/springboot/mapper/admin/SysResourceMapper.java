package com.intflag.springboot.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intflag.springboot.entity.admin.SysResource;
import com.intflag.springboot.entity.admin.SysResourceExample;

public interface SysResourceMapper {
    int countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(String resourceId);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    List<SysResource> selectByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(String resourceId);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

    /**
      * 根据用户查询对应的所有权限
     * @param userId
     * @return
     */
	List<SysResource> selectAllByUserId(String userId);

	/**
	 *  根据用户查询对应顶级权限
	 * @param userId
	 * @return
	 */
	List<SysResource> selectByUserId(String userId);

	/**
	 * 根据菜单类型查找
	 * @param string
	 * @return
	 */
	List<SysResource> selectByType(String string);

	/**
	 * 根据类型查找菜单
	 * @param string
	 * @return
	 */
	List<SysResource> selectMenuByType(String string);
}