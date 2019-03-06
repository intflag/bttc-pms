package com.intflag.springboot.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intflag.springboot.entity.admin.SysLog;
import com.intflag.springboot.entity.admin.SysLogExample;

public interface SysLogMapper {
	int countByExample(SysLogExample example);

	int deleteByExample(SysLogExample example);

	int deleteByPrimaryKey(String logId);

	int insert(SysLog record);

	int insertSelective(SysLog record);

	List<SysLog> selectByExample(SysLogExample example);

	SysLog selectByPrimaryKey(String logId);

	int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

	int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

	int updateByPrimaryKeySelective(SysLog record);

	int updateByPrimaryKey(SysLog record);
}