package com.intflag.springboot.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intflag.springboot.entity.admin.SysRoleResExample;
import com.intflag.springboot.entity.admin.SysRoleResKey;

public interface SysRoleResMapper {
    int countByExample(SysRoleResExample example);

    int deleteByExample(SysRoleResExample example);

    int deleteByPrimaryKey(SysRoleResKey key);

    int insert(SysRoleResKey record);

    int insertSelective(SysRoleResKey record);

    List<SysRoleResKey> selectByExample(SysRoleResExample example);

    int updateByExampleSelective(@Param("record") SysRoleResKey record, @Param("example") SysRoleResExample example);

    int updateByExample(@Param("record") SysRoleResKey record, @Param("example") SysRoleResExample example);
}