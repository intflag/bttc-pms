package com.intflag.springboot.mapper.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.intflag.springboot.entity.admin.SysTemplateparams;
import com.intflag.springboot.entity.admin.SysTemplateparamsExample;

@SuppressWarnings("rawtypes")
public interface SysTemplateparamsMapper {
	int countByExample(SysTemplateparamsExample example);

	int deleteByExample(SysTemplateparamsExample example);

	int deleteByPrimaryKey(String templateId);

	int insert(SysTemplateparams record);

	int insertSelective(SysTemplateparams record);

	List<SysTemplateparams> selectByExample(SysTemplateparamsExample example);

	SysTemplateparams selectByPrimaryKey(String templateId);

	int updateByExampleSelective(@Param("record") SysTemplateparams record,
			@Param("example") SysTemplateparamsExample example);

	int updateByExample(@Param("record") SysTemplateparams record, @Param("example") SysTemplateparamsExample example);

	int updateByPrimaryKeySelective(SysTemplateparams record);

	int updateByPrimaryKey(SysTemplateparams record);

	/**
	 * 获取数据库所有表
	 * 
	 * @param dataBase
	 * @return
	 */
	List<Map> selectTablesByDataBase(String dataBase);

	/**
	 * 根据表名查找字段
	 * 
	 * @param dataBase
	 * @param tableName
	 * @return
	 */
	List<Map> selectFieldByTableName(@Param("dataBase")String dataBase, @Param("tableName")String tableName);
	/**
	 * 根据表名查找主键
	 * 
	 * @param dataBase
	 * @param tableName
	 * @return
	 */
	String selectPrimaryKeyByTableName(@Param("dataBase")String dataBase, @Param("tableName")String tableName);
}