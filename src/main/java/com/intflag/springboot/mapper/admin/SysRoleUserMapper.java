package com.intflag.springboot.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intflag.springboot.entity.admin.SysRoleUserExample;
import com.intflag.springboot.entity.admin.SysRoleUserKey;

public interface SysRoleUserMapper {
    int countByExample(SysRoleUserExample example);

    int deleteByExample(SysRoleUserExample example);

    int deleteByPrimaryKey(SysRoleUserKey key);

    int insert(SysRoleUserKey record);

    int insertSelective(SysRoleUserKey record);

    List<SysRoleUserKey> selectByExample(SysRoleUserExample example);

    int updateByExampleSelective(@Param("record") SysRoleUserKey record, @Param("example") SysRoleUserExample example);

    int updateByExample(@Param("record") SysRoleUserKey record, @Param("example") SysRoleUserExample example);
}